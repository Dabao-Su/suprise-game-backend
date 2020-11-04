package com.dabao.supriseTrip.action;

import com.dabao.supriseTrip.service.SupriseTripService;
import com.dabao.supriseTrip.vo.Journey;
import com.dabao.supriseTrip.vo.Punishment;
import com.dabao.supriseTrip.vo.QuestionClue;
import com.dabao.supriseTrip.vo.Scene;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by dabao on 2020-09-28.
 */
@RestController
@RequestMapping(value = "journey")
public class JourneyAction {

    @Autowired
    private SupriseTripService supriseTripService;

    @RequestMapping(value = "getJourneyById")
    public Journey getJourneyById(@RequestParam(value = "playName") String playName, @RequestParam(value = "journeyId") Long journeyId){
        Journey journey = supriseTripService.getJourneyById(journeyId);

        if(journey == null) throw new IllegalArgumentException("很抱歉，此趟旅程不存在");
        if(!journey.getPlayerName().equalsIgnoreCase(playName)) throw new IllegalArgumentException("很抱歉，您没有权限开启此趟旅程哦");

        return journey;
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
        return supriseTripService.getAllPublicPunishment(pageSize, (pageNum - 1) * pageSize);
    }

    @RequestMapping(value = "getAllPublicQuestionOrClues")
    public List<QuestionClue> getAllPublicQuestionOrClues(@RequestParam(value = "type")int type,
                                                          @RequestParam(value = "pageNum",defaultValue = "1") int pageNum,
                                                          @RequestParam(value = "pageSize",defaultValue = "20") int pageSize){
        return supriseTripService.getAllPublicQuestionOrClues(type, pageSize, (pageNum - 1) * pageSize);
    }

    @RequestMapping(value = "addScene")
    public Scene addScene(@RequestParam(value = "scene") Scene scenes){
        if(scenes.getId() != 0L) throw new IllegalArgumentException("场景已存在，请勿重复提交数据");
        supriseTripService.addScene(scenes);
        return scenes;
    }

    @RequestMapping(value = "addQuestionOrClue")
    public Scene addQuestionOrClue(@RequestParam(value = "scenes") Scene scene){
        if(scene.getId() != 0L) throw new IllegalArgumentException("场景已存在，请勿重复提交数据");
        supriseTripService.addScene(scene);
        return scene;
    }

}
