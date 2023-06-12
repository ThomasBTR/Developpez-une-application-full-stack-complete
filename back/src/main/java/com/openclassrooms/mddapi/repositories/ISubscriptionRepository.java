package com.openclassrooms.mddapi.repositories;

import com.openclassrooms.mddapi.models.Subscription;
import com.openclassrooms.mddapi.models.SubscriptionPrimaryKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISubscriptionRepository extends JpaRepository<Subscription, SubscriptionPrimaryKey> {
}
