/*
 * Copyright 2019 liaochong
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.liaochong.myexcel.core;

import com.github.liaochong.myexcel.core.constant.Constants;
import org.apache.poi.ss.util.CellReference;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.Map;

import static org.apache.poi.xssf.usermodel.XSSFRelation.NS_SPREADSHEETML;

/**
 * xlsx文件合并单元格处理器
 *
 * @author liaochong
 * @version 1.0
 */
public class XSSFSheetMergeXMLHandler extends DefaultHandler {

    private final Map<String, String> mergeCells;

    public XSSFSheetMergeXMLHandler(Map<String, String> mergeCells) {
        this.mergeCells = mergeCells;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (uri != null && !uri.equals(NS_SPREADSHEETML)) {
            return;
        }
        if (!"mergeCell".equals(localName) && !"x:mergeCell".equals(localName)) {
            return;
        }
        String ref = attributes.getValue("ref");
        if (ref == null) {
            return;
        }
        String[] ranges = ref.split(Constants.COLON);
        CellReference first = new CellReference(ranges[0]);
        int firstRoNum = first.getRow();
        int firstColNum = first.getCol();
        CellReference last = new CellReference(ranges[1]);
        int lastRoNum = last.getRow();
        int lastColNum = last.getCol();
        final String valuePos = firstRoNum + "_" + firstColNum;
        for (int i = firstRoNum; i <= lastRoNum; i++) {
            for (int j = firstColNum; j <= lastColNum; j++) {
                String mergeCellPos = i + "_" + j;
                if (!mergeCellPos.equals(valuePos)) {
                    mergeCells.put(mergeCellPos, valuePos);
                }
            }
        }
    }
}
