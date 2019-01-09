package Exceptions.M05_Channel;

import Exceptions.PersonalizedException;

public class ChannelNotFoundException extends PersonalizedException {
    public ChannelNotFoundException(){}
    public ChannelNotFoundException(Exception e){
        super(e);
    }
    public ChannelNotFoundException(String msg){
        super(msg);
    }
    public ChannelNotFoundException(String msg, Exception e){
        super(msg, e);
    }
}
