package com.dabao;

import com.dabao.supriseTrip.dao.ISupriseTripDao;
import com.dabao.supriseTrip.service.SupriseTripService;
import com.dabao.supriseTrip.vo.Journey;
import com.dabao.supriseTrip.vo.QuestionClue;
import com.dabao.supriseTrip.vo.Scene;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class SuperisetripApplicationTests {

	@Autowired
	SupriseTripService supriseTripService;

	@Autowired
	ISupriseTripDao iSupriseTripDao;

	@Test
	void contextLoads() {
//		Journey journey = supriseTripService.getJourneyById(1L);
//		List<Journey> allPublicJourney = supriseTripService.getJourneys(null, true, null, null);
//
//		List<Scene> scenes = supriseTripService.getAllPublicScenes(20,0);
//		List<Punishment> punishments = supriseTripService.getAllPublicPunishment(20,0);

		List<QuestionClue> questionOrClues = supriseTripService.getAllPublicQuestionOrClues(QuestionClue.TYPE_CLUE, 20, 0);

		return;
	}

//	@Test
	void addSceneTest(){
		Scene scenes = new Scene();
		scenes.setLocation("高中教室");
		scenes.setBehavior("音乐课上开心而专注的看漫画");
		scenes.setProblem("老师播放了三段音乐并叫了没听清问题的你起来回答问题");
		scenes.setPurpose("老师提问的问题");
		supriseTripService.addScene(scenes);
		Assertions.assertTrue(scenes.getId() > 0L);
	}

//	@Test
	void addQuestionOrClueTest(){
		QuestionClue questionClue = new QuestionClue();
		questionClue.setType(QuestionClue.TYPE_QUESTION);
		questionClue.setName("以下哪个关于撒哈拉的传说不是真的");
		questionClue.setTrueAnswer("西瓜来自撒哈拉,由骑着撒哈拉双峰骆驼的商人经丝绸之路传入新疆");
		questionClue.setWrongAnswer1("三毛说过:每想你一次,天上飘落一粒沙,从此便形成了撒哈拉");
		questionClue.setWrongAnswer2("撒哈拉沙漠每年给亚马逊热带雨林输送了大量的矿物质沙尘,滋润了亚马逊盆地");
		supriseTripService.addQuestionOrClue(questionClue);
		Assertions.assertTrue(questionClue.getId() > 0L);
	}

//	@Test
	void addJourneyTest(){
		Journey journey = new Journey();
		journey.setCreatorName("AAA");
		journey.setPlayerName("BBBB");
		journey.setPunishmentFrequency(100);

		Scene scene = new Scene();
		scene.setId(1L);
		ArrayList<Scene> scenes = new ArrayList<>();
		scenes.add(scene);
		journey.setScenes(scenes);

		supriseTripService.addJourney(journey);
		Assertions.assertTrue(journey.getId()>0);
	}

}
