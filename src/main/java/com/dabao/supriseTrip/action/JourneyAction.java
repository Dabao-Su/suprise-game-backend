package com.dabao.supriseTrip.action;

import com.dabao.supriseTrip.service.SupriseTripService;
import com.dabao.supriseTrip.vo.Journey;
import com.dabao.supriseTrip.vo.Punishment;
import com.dabao.supriseTrip.vo.Question;
import com.dabao.supriseTrip.vo.Scene;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by dabao on 2020-09-28.
 */
@RestController
@RequestMapping(value = "journey")
public class JourneyAction {

    @Autowired
    private SupriseTripService supriseTripService;

    @RequestMapping("getJourneyById")
    public Journey getJourneyById(@RequestParam(value = "playName") String playName, @RequestParam(value = "journeyId") Long journeyId, HttpServletRequest request){
        Journey journey = Optional.ofNullable(supriseTripService.getJourneyById(journeyId)).filter( j -> j.getPlayerName().equalsIgnoreCase(playName.trim())).orElseThrow(() -> {
            return new IllegalArgumentException("很抱歉，此趟旅程不存在或您没有权限开启此趟旅程");
        });

        Queue sceneQueue = new LinkedBlockingQueue();
        sceneQueue.addAll(supriseTripService.getAllSceneInfoByJourneyId(journey.getId()));
        request.getSession().setAttribute("scenes", sceneQueue);
        return journey;
    }

    /**
     * 检查答案并且移到下一个场景
     * @param answers
     * @param request
     * @return
     */
    @RequestMapping("checkAnswer")
    public Boolean checkAnswer(@RequestParam("answers") Set<String> answers, HttpServletRequest request){
        Queue<Scene> scenes = Optional.ofNullable(request.getSession().getAttribute("scenes")).map(sceneQueue -> (Queue) sceneQueue).filter(t -> !t.isEmpty()).orElseThrow(() -> {
            return new IllegalArgumentException("行程已结束");
        });
        Scene scene = Optional.ofNullable(scenes.poll()).orElseThrow(() -> {
            return new IllegalArgumentException("行程已结束");
        });
        Question question = Optional.ofNullable(scene.getQuestion()).orElseThrow(() -> {
            return new IllegalArgumentException("行程没有设置问题");
        });
        Set<String> collectAnswers = question.getAnswer();
        if(collectAnswers.size() == answers.size() && collectAnswers.containsAll(answers)) return Boolean.TRUE;
        return Boolean.FALSE;
    }

    @RequestMapping(value = "getAllPublicJourneys")
    public List<Journey> getAllPublicJourneys(@RequestParam(value = "pageNum",defaultValue = "1") int pageNum,@RequestParam(value = "pageSize",defaultValue = "20") int pageSize){
        return supriseTripService.getJourneys(null, true, pageSize, (pageNum-1)*pageSize);
    }

    @RequestMapping(value = "getAllPublicScenes")
    public List<Scene> getAllPublicScenes(@RequestParam(value = "pageNum",defaultValue = "1") int pageNum,@RequestParam(value = "pageSize",defaultValue = "20") int pageSize){
        return supriseTripService.getAllPublicScenes(pageSize, (pageNum - 1) * pageSize);
    }

    @RequestMapping(value = "getAllPublicPunishments")
    public List<Punishment> getAllPublicPunishments(@RequestParam(value = "pageNum",defaultValue = "1") int pageNum,@RequestParam(value = "pageSize",defaultValue = "20") int pageSize){
        return supriseTripService.getAllPublicPunishments(pageSize, (pageNum - 1) * pageSize);
    }

    @RequestMapping(value = "getAllPublicQuestions")
    public List<Question> getAllPublicQuestions(@RequestParam(value = "pageNum",defaultValue = "1") int pageNum,
                                                      @RequestParam(value = "pageSize",defaultValue = "20") int pageSize){
        return supriseTripService.getAllPublicQuestions(pageSize, (pageNum - 1) * pageSize);
    }

    @RequestMapping(value = "addScene")
    public Scene addScene(@RequestParam(value = "scene") Scene scenes){
        if(scenes.getId() != 0L) throw new IllegalArgumentException("场景已存在，请勿重复提交数据");
        supriseTripService.addScene(scenes);
        return scenes;
    }

    @RequestMapping(value = "addQuestion")
    public Question addQuestion(@RequestParam(value = "question") Question question){
        if(question.getId() != 0L) throw new IllegalArgumentException("问题已存在，请勿重复提交数据");
        supriseTripService.addQuestion(question);
        return question;
    }

//    @RequestMapping(value = "getPrize")
//    public String getPrize(){
//
//    }

}
