package com.urlshortener.doamin;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "url_shortener")
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UrlShortener {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false, unique = true)
    private String url;
    @Column
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

    private UrlShortener(String url) {
        this.url = url;
    }

    public void addSearchCount() {
        this.searchCount = this.searchCount + 1L;
    }

    public void addShortKey(String shortKey) {
        this.shortKey = shortKey;
    }

    public static UrlShortener createUrlShortener(String url) {
        return new UrlShortener(url);
    }
}
