package com.jyx.jiaoshoujia.menu;


import com.jyx.jiaoshoujia.iterface.MenuInterface;

public enum CodeMessageMenu implements MenuInterface {

        // 数据操作错误定义

        ACCOUNT_BUSYS(400,"账号不存在"),

        PASSWORD_BUSYS(400,"密码错误"),

        USER_NOT_LEGALLY(404,"用户身份不合法"),

        USER_ALREADY_EXIST(405,"用户已经存在");






        /**
         * 错误码
         */
        private final Integer codeMessage;

        /**
         * 错误描述
         */
        private final String textMessage;



        CodeMessageMenu(Integer resultCode, String resultMsg) {
            this.codeMessage = resultCode;
            this.textMessage = resultMsg;
        }


    @Override
    public Integer getCodeMessage() {
        return codeMessage;
    }

    @Override
    public String getTextMessage() {
        return textMessage;
    }
}
