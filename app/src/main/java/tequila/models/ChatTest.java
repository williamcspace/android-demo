package tequila.models;

/**
 * Created by williamc1986 on 8/7/15.
 */
public class ChatTest {

    public static final Integer CHAT_TO = 1;
    public static final Integer CHAT_FROM = 2;

    private Boolean isTimeShown;
    private Integer mChatType;
    private Object mMessage;
    private String mText;
    private String mTime;

    public ChatTest(){
        mChatType = CHAT_TO;
    }

    public void setChatType(Integer type){
        mChatType = type;
    }

    public void setText(String text){
        mText = text;
    }

    public String getText(){
        return mText;
    }

    public void setTime(String time){
        mTime = time;
    }
    public String getTime(){
        return mTime;
    }

    public Integer getChatType() {
        return mChatType;
    }

    public void setMessageObject(Object message){
        mMessage = message;
    }

    public Object getMessageObject(){
        return mMessage;
    }

    public void setTimeShown(Boolean bool){
        isTimeShown = bool;
    }

    public Boolean isTimeShown(){
        return isTimeShown;
    }
}
