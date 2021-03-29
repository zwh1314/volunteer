package com.example.volunteer.Entity;

import javax.persistence.Column;
import java.io.Serializable;

public class ActivityUserKey implements Serializable {
    @Column(name = "id",nullable = false)
    private long id;

    @Column(name = "activity_id",nullable = false)
    private long activityId;

    @Column(name = "user_id",nullable = false)
    private long userId;
}
