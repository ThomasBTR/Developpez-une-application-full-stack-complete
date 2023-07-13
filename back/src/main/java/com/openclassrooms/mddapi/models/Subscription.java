package com.openclassrooms.mddapi.models;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * The type Subscription.
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "SUBSCRIPTIONS")
public class Subscription implements Serializable {

    private static final long serialVersionUID = -23854044588823410L;

    @EmbeddedId
    private SubscriptionPrimaryKey id;

    /**
     * The User.
     */
    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    User user;

    /**
     * The Theme.
     */
    @ManyToOne
    @MapsId("themeId")
    @JoinColumn(name = "theme_id", insertable = false, updatable = false)
    ThemeEntity theme;

}
