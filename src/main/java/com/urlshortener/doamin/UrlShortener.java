package com.urlshortener.doamin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "member")
@AllArgsConstructor
@NoArgsConstructor
public class UrlShortener {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String url;
    @Column(nullable = false, unique = true)
    private String shortKey;

    @Column(name = "search_count")
    private Long searchCount = 0L;

    @CreationTimestamp
    @Column(name = "create_dt", nullable = false, updatable = false)
    private LocalDateTime createDt;

    @UpdateTimestamp
    @Column(name = "update_dt")
    private LocalDateTime updateDt;

    @Builder(builderMethodName = "mockMvcUrlShortener")
    private UrlShortener(String url, String shortKey) {
        this.url = url;
        this.shortKey = shortKey;
    }

    public static UrlShortener createUrlShortener(String url, String shortKey){
        return new UrlShortener(url, shortKey);
    }
}
