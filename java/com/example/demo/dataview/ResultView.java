package com.example.demo.dataview;

public class ResultView<T> {

    public String msg;

    public T data;

    public int code;

    public ResultView() {
    }

    public ResultView(String msg, T data, int code) {
        this.msg = msg;
        this.data = data;
        this.code = code;
    }



    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public static ResultView success(Object object){
        ResultView resultVO=new ResultView();
        resultVO.setData(object);
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        return resultVO;
    }


    public static ResultView error(String msg){
        ResultView resultVO=new ResultView();
        resultVO.setCode(1);
        resultVO.setMsg(msg);
        return resultVO;
    }
}

