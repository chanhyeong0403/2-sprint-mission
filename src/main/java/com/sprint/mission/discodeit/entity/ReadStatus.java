package com.sprint.mission.discodeit.entity;

import com.sprint.mission.discodeit.entity.base.BaseUpdatableEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;
import lombok.NoArgsConstructor;

@Getter
@Table(name = "read_statuses")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReadStatus extends BaseUpdatableEntity {

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "user_id")
  private User user;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "channel_id")
  private Channel channel;

  @Column(nullable = false)
  private Instant lastReadAt;

  public ReadStatus(User user, Channel channel, Instant lastReadAt) {
    this.user = user;
    this.channel = channel;
    this.lastReadAt = lastReadAt;
  }

  public void update(Instant newLastReadAt) {
    if (newLastReadAt != null && !newLastReadAt.equals(this.lastReadAt)) {
      this.lastReadAt = newLastReadAt;
    }
  }
}
