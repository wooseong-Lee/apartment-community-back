package com.aptalk.domain;

import com.aptalk.dto.CommunityRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"name", "zipCode"})
})
@Getter
@NoArgsConstructor
public class Community {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    public String name;

    @Column(nullable = false)
    public String zipCode;

    public String notice;

    public Community(Long id) {
        this.id = id;
    }

    private Community(String name, String zipCode, String notice) {
        this.name = name;
        this.zipCode = zipCode;
        this.notice = notice;
    }

    public static Community from(CommunityRequest communityRequest) {
        return new Community(communityRequest.getName(), communityRequest.getZipCode(), communityRequest.getNotice());
    }

    public Community updateCommunity(CommunityRequest communityRequest) {
        if(communityRequest.getName() != null) this.name = communityRequest.getName();
        if(communityRequest.getZipCode() != null) this.zipCode = communityRequest.getZipCode();
        if(communityRequest.getNotice() != null) this.notice = communityRequest.getNotice();
        return this;
    }
}
