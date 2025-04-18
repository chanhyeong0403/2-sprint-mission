package com.sprint.mission.discodeit.entity;

import com.sprint.mission.discodeit.entity.base.BaseUpdatableEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import lombok.AccessLevel;
import lombok.Getter;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import lombok.NoArgsConstructor;

@Getter
@Table(name = "messages")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Message extends BaseUpdatableEntity {

  @Column(nullable = false)
  private String content;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "channel_id")
  private Channel channel;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "author_id")
  private User author;

  @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
  @JoinTable(
      name = "message_attachments",
      joinColumns = @JoinColumn(name = "message_id"),
      inverseJoinColumns = @JoinColumn(name = "attachment_id")
  )
  private List<BinaryContent> attachments = new ArrayList<>();

  public Message(String content, Channel channel, User author, List<BinaryContent> attachments) {
    this.channel = channel;
    this.content = content;
    this.author = author;
    this.attachments = attachments;
  }

  public void update(String newContent) {
    if (newContent != null && !newContent.equals(this.content)) {
      this.content = newContent;
    }
  }
}
