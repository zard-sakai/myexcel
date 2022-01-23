package com.github.liaochong.example.controller;

import com.github.liaochong.example.pojo.ArtCrowd;
import com.github.liaochong.myexcel.core.DefaultStreamExcelBuilder;
import com.github.liaochong.myexcel.utils.AttachmentExportUtil;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author liaochong
 * @version 1.0
 */
@Controller
public class DefaultStreamExcelBuilderExampleController {

    private ExecutorService executorService = Executors.newFixedThreadPool(24);

    @GetMapping("/default/excel/stream/example")
    public void streamBuild(HttpServletResponse response) throws Exception {
        try (DefaultStreamExcelBuilder<ArtCrowd> defaultExcelBuilder = DefaultStreamExcelBuilder.of(ArtCrowd.class)
                .threadPool(executorService)
                .start()) {
            for (int i = 0; i < 1; i++) {
                // defaultExcelBuilder.append(this.getDataList());
                defaultExcelBuilder.asyncAppend(this::getDataList);
            }
            Workbook workbook = defaultExcelBuilder.build();
            AttachmentExportUtil.export(workbook, "艺术生信息1", response);
        }
    }

    @GetMapping("/default/excel/stream/continue/example")
    public void streamBuildWithContinue(HttpServletResponse response) throws Exception {
        DefaultStreamExcelBuilder<ArtCrowd> defaultExcelBuilder = DefaultStreamExcelBuilder.of(ArtCrowd.class)
                .threadPool(executorService)
                .start();
        for (int i = 0; i < 1; i++) {
            defaultExcelBuilder.asyncAppend(this::getDataList);
        }
        Workbook workbook = defaultExcelBuilder.build();

        DefaultStreamExcelBuilder<ArtCrowd> defaultStreamExcelBuilder = DefaultStreamExcelBuilder.of(ArtCrowd.class, workbook)
                .threadPool(executorService)
                .sheetName("sheet2")
                .start();
        for (int i = 0; i < 1; i++) {
            defaultStreamExcelBuilder.asyncAppend(this::getDataList);
        }
        workbook = defaultStreamExcelBuilder.build();
        AttachmentExportUtil.export(workbook, "艺术生信息", response);
    }

    private List<ArtCrowd> getDataList() {
        List<ArtCrowd> dataList = new ArrayList<>();
        for (int i = 0; i < 1000000; i++) {
            ArtCrowd artCrowd = new ArtCrowd();
            if (i % 2 == 0) {
                artCrowd.setName("Tom");
                artCrowd.setAge(19);
                artCrowd.setGender("Man");
                artCrowd.setPaintingLevel("一级证书");
                artCrowd.setDance(false);
                artCrowd.setAssessmentTime(LocalDateTime.now());
                artCrowd.setHobby("摔跤");
            } else {
                artCrowd.setName("Marry");
                artCrowd.setAge(18);
                artCrowd.setGender("Woman");
                artCrowd.setPaintingLevel("一级证书");
                artCrowd.setDance(true);
                artCrowd.setAssessmentTime(LocalDateTime.now());
                artCrowd.setHobby("钓鱼");
            }
            dataList.add(artCrowd);
        }
        return dataList;
    }
}
