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
private ErrorResponse error;

public Translate getTranslate(){
    return new Translate(contents.text,contents.translated);
}

    public String getError(){
        return error.message;
    }


    private class ContentsResponse {
        private String translated;
        private String text;

    }

    private class ErrorResponse {
        private String code;
        private String message;

    }
}
