package com.campus.dao.pojo;

public class TblMycollection {
    /** */
    private Integer id;

    /** 参训学院ID*/
    private Integer trainerId;

    /** 收藏事件ID*/
    private Integer eventId;

    /** 收藏类别*/
    private Byte type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(Integer trainerId) {
        this.trainerId = trainerId;
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }
}