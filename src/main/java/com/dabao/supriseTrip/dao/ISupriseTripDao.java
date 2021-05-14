package com.dabao.supriseTrip.dao;

import com.dabao.supriseTrip.vo.Journey;
import com.dabao.supriseTrip.vo.Punishment;
import com.dabao.supriseTrip.vo.Question;
import com.dabao.supriseTrip.vo.Scene;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by dabao on 2020-09-18.
 */
@Mapper
public interface ISupriseTripDao {

    Journey getJourneyById(Long journeyId);

    List<Journey> getJourneys(@Param(value = "id") Long journeyId, @Param(value = "isPublic") Boolean isPublic, @Param(value = "pageSize") Integer pageSize, @Param(value = "offset") Integer offset);

    List<Scene> getAllPublicScenes(@Param(value = "pageSize") int pageSize, @Param(value = "offset") int offset);

    List<Punishment> getAllPublicPunishments(@Param(value = "pageSize") int pageSize, @Param(value = "offset") int offset);

    List<Question> getAllPublicQuestions(@Param(value = "pageSize") int pageSize, @Param(value = "offset") int offset);

    List<Scene> getAllSceneInfoByJourneyId(@Param("journeyId") long journeyId);
    
    void addScene(Scene scenes);

    
    void addQuestion(Question question);

    
    void addPunishment(Punishment punishment);

    
    void addJourney(Journey journey);

    
    void addJourneyScene(@Param("journeyId") long journeyId,@Param("sceneId") long sceneId,
                         @Param("questionId") long questionId,@Param("clue") String clue,@Param(value = "prize") String prize);

    
    void addJourneyPunishment(@Param("journeyId") long journeyId,@Param("punishmentId") long punishmentId);
}
