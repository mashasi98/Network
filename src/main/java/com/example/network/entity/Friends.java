package com.example.network.entity;


import com.example.network.dto.UserDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "friends")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class Friends  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    @JoinColumn(name = "friend_id", nullable = false)
    private User userFriend;

    public Friends(User user, User userFriend) {
        this.user = user;
        this.userFriend = userFriend;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Friends)) return false;
        Friends friends = (Friends) o;
        return id.equals(friends.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
