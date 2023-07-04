package com.openclassrooms.mddapi.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * The type Subscription primary key.
 */
@Embeddable
@Getter
@Setter
public class SubscriptionPrimaryKey implements Serializable {
    private static final long serialVersionUID = -2323495044588823410L;

    /**
     * The User id.
     */
    @Column(name = "user_id")
    Long userId;


    /**
     * The Theme id.
     */
    @Column(name = "theme_id")
    Long themeId;
}
