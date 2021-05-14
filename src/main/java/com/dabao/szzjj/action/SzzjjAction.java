package com.dabao.szzjj.action;

import com.dabao.szzjj.service.EsSourceService;
import com.dabao.szzjj.service.HouseSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


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

    @RequestMapping("loadEsSource")
    public void loadEsSource() throws IOException, NoSuchFieldException, IllegalAccessException {
        esSourceService.loadEsSource();
    }

    @RequestMapping("loadEsPrice")
    public void loadEsPrice() throws IOException {
        houseSourceService.loadEsPrice();
    }
}
