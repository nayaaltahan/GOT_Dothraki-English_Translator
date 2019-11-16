package com.example.got.rds;

import com.example.got.model.Translate;

public class DothrakiResponse {
/*
 * layout :
 * {
 *   "success": {
 *     "total": 1
 *   },
 *   "contents": {
 *     "translated": "Hash yeri ray tih erinak’s zhavorsa?",
 *     "text": "Have you seen my lady’s dragon?",
 *     "translation": "dothraki"
 *   }
 * }
 */

private ContentsResponse contents;

public Translate getTranslate(){
    return new Translate(contents.text,contents.translated);
}

    private class ContentsResponse {
        private String translated;
        private String text;

    }
}
