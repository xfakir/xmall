package cn.xfakir.xmall.common.web;

import cn.xfakir.xmall.common.constant.ResultCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class XmResult {
    private Integer code;
    private String msg;
    private Object data;

    public XmResult() {
    }
    public XmResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
        this.data = null;
    }

    public XmResult(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static XmResult success(String msg, Object data) {
        return new XmResult(ResultCode.SUCCESS,msg,data);
    }

    public static XmResult failure(String msg,Object data) {
        return new XmResult(ResultCode.FAILURE,msg,data);
    }
}
