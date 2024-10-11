package com.is.globalTieto.tietoModels;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class Settings {
    @Getter
    @Setter
    private String PaymentMode;
    @Getter
    @Setter
    private String AccCcy;
    @Getter
    @Setter
    private String TranzCcy;
    @Getter
    @Setter
    private String TranzType;
}
