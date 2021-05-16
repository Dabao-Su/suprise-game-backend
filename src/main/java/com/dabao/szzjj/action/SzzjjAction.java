package com.dabao.szzjj.action;

import com.dabao.szzjj.service.EsSourceService;
import com.dabao.szzjj.service.HouseSourceService;
import com.dabao.szzjj.service.ProjectInfoService;
import com.dabao.szzjj.vo.EsSource;
import com.dabao.szzjj.vo.PresellInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;


/**
 * Created by dabao on 2021-05-12.
 */
@RestController
@RequestMapping(value = "szzjj")
public class SzzjjAction {

    @Autowired
    private EsSourceService esSourceService;
    @Autowired
    private HouseSourceService houseSourceService;
    @Autowired
    private ProjectInfoService projectInfoService;

    @RequestMapping("loadEsSource")
    public List<EsSource> loadEsSource() throws IOException, NoSuchFieldException, IllegalAccessException, InstantiationException, ParseException {
        return esSourceService.loadEsSource();
    }

    @RequestMapping("loadEsPrice")
    public void loadEsPrice() throws IOException {
        houseSourceService.loadEsPrice();
    }

    @RequestMapping("loadProjectInfo")
    public List<PresellInfo> loadProjectInfo() throws IOException, NoSuchFieldException, IllegalAccessException, InstantiationException, ParseException {
        return projectInfoService.getPresaleInfo();
    }
}
