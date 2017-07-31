package com.liufeng.npc.bean;

/**
 * 匹配kindEditor的数据返回
 */
public class EditorMsg {
    //0 没问题 1 有问题
    public int error = 0;
    public String url = "";
    public String message="";

    @Override
    public String toString() {
        return "EditorMsg{" +
                "error=" + error +
                ", url='" + url + '\'' +
                ", message='" + message + '\'' +
                '}';
    }

    public static EditorMsg getError(String message){
        EditorMsg editorMsg = new EditorMsg();
        editorMsg.setError(1);
        editorMsg.setMessage(message);
        return editorMsg;
    }
    public static EditorMsg getError(){
        EditorMsg editorMsg = new EditorMsg();
        editorMsg.setError(1);
        editorMsg.setMessage("操作失败");
        return editorMsg;
    }

    public static EditorMsg getSuccess(){
        EditorMsg editorMsg = new EditorMsg();
        editorMsg.setError(0);
        editorMsg.setMessage("操作成功");
        return editorMsg;
    }
    public static EditorMsg getSuccess(String message){
        EditorMsg editorMsg = new EditorMsg();
        editorMsg.setError(0);
        editorMsg.setMessage(message);
        return editorMsg;
    }
    public static EditorMsg getSuccessWithUrl(String message,String url){
        EditorMsg editorMsg = new EditorMsg();
        editorMsg.setError(0);
        editorMsg.setUrl(url);
        editorMsg.setMessage(message);
        return editorMsg;
    }
    public static EditorMsg getSuccessWithUrl(String url){
        EditorMsg editorMsg = new EditorMsg();
        editorMsg.setError(0);
        editorMsg.setUrl(url);
        editorMsg.setMessage("上传成功");
        return editorMsg;
    }


    public void setError(int error) {
        this.error = error;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }




}
