package com.dabao.supriseTrip.service;

import com.dabao.supriseTrip.dao.ISupriseTripDao;
import com.dabao.supriseTrip.vo.*;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by dabao on 2020-10-14.
 */
@Service
public class SupriseTripService {

    @Autowired
    private ISupriseTripDao supriseTripDao;

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Transactional
    public Journey getJourneyById(Long journeyId){
        return supriseTripDao.getJourneyById(journeyId);
    }

    @Transactional
    public List<Journey> getJourneys(Long journeyId, Boolean isPublic, Integer pageSize, Integer offset){
        return supriseTripDao.getJourneys(journeyId, isPublic, pageSize, offset);
    }

    @Transactional
    public List<Scene> getAllPublicScenes(int pageSize, int offset){
        return supriseTripDao.getAllPublicScenes(pageSize, offset);
    }

    @Transactional
    public List<Punishment> getAllPublicPunishment( int pageSize, int offset){
        return supriseTripDao.getAllPublicPunishment(pageSize , offset);
    }

    @Transactional
    public List<QuestionClue> getAllPublicQuestionOrClues(int type, int pageSize, int offset){
        return supriseTripDao.getAllPublicQuestionOrClues(type , pageSize, offset);
    }

    @Transactional
    public void addScene(Scene scene){
        supriseTripDao.addScene(scene);
    }

    @Transactional
    public void addQuestionOrClue(QuestionClue questionClue){
        supriseTripDao.addQuestionOrClue(questionClue);
    }

    @Transactional
    public void addPunishment(Punishment punishment){
        supriseTripDao.addPunishment(punishment);
    }

    @Transactional
    public void addJourney(Journey journey){
        if(journey.getId() > 0L) return;

        SqlSession sqlSession =sqlSessionFactory.openSession();
        try {
            sqlSessionFactory.openSession();
            supriseTripDao.addJourney(journey);

            long journeyId = journey.getId();
            long punishmentFrequency = journey.getPunishmentFrequency();
            List<Scene> scenes = journey.getScenes();
            List<Punishment> punishments = journey.getPunishments();

            if( scenes == null || scenes.size() == 0 ){
                throw new IllegalArgumentException("场景不能为空");
            }

            if( punishmentFrequency != 0L && punishmentFrequency < scenes.size() && ( punishments == null || punishments.size() == 0 )){
                throw new IllegalArgumentException("惩罚不能为空");
            }

            scenes.forEach(scene -> {
                if(scene.getQuestion() == null){
                    throw new IllegalArgumentException("场景对应的问题不能为空");
                }
                supriseTripDao.addJourneyScene(journeyId, scene.getId(), scene.getQuestion().getId(), scene.getClue() != null?0L:scene.getClue().getId(), scene.getPrize());
            });

            punishments.forEach(punishment -> {
                supriseTripDao.addJourneyPunishment(journeyId,punishment.getId());
            });
            sqlSession.commit();
            sqlSession.clearCache();
        }catch (Exception ex){
            sqlSession.rollback();
            throw ex;
        }finally {
            if(sqlSession != null){
                sqlSession.close();
            }
        }

    }
}
