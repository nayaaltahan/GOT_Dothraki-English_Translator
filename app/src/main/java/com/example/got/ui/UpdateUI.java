package com.example.got.ui;

import com.example.got.model.Translate;

public interface UpdateUI {
    void updateUiWithTranslate(Translate translate);
    void updateUiWithError(String error);
}


