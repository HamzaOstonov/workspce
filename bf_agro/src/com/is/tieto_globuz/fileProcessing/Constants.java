// 
// Decompiled by Procyon v0.5.36
// 

package com.is.tieto_globuz.fileProcessing;

public class Constants
{
    public static final Long FILE_ACCEPTED = 1L;
    public static final Long FILE_PROCESSED = 2L;
    public static final Long FILE_PROCESSED_WITH_ERRORS = 3L;
    public static final Long FILE_PROCESSED_FIRST_STEP = 8L; //Первично обработан.ожидает группировку и проводки
    public static final Long FILE_FIRST_STEP_WITH_ERRORS = 10L; //Ошибка первичной обработки
    public static final Long FILE_PROCESSED_SECOND_STEP = 11L; //Вторично обработан. сгруппировано.
    public static final Long FILE_SECOND_PARTIALLY_PROCESSED = 12L; //Частично вторично обработан

    public static final Long RECORD_ACCEPTED;
    public static final Long RECORD_PROCESSED;
    public static final Long RECORD_ERROR;
    public static final Long ID_TIETO_DIRECTORY;
    public static final String BUTTON_HANDLER_IMAGE = "/images/file.png";
    
    static {
        RECORD_ACCEPTED = 1L;
        RECORD_PROCESSED = 2L;
        RECORD_ERROR = 3L;
        ID_TIETO_DIRECTORY = 3L;
    }
}
