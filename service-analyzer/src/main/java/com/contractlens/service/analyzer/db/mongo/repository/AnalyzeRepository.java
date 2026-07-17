package com.contractlens.service.analyzer.db.mongo.repository;

import com.contractlens.service.analyzer.db.mongo.dao.AnalyzeSpecDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AnalyzeRepository extends MongoRepository<AnalyzeSpecDocument, String> {

    @Query(
            value = """
        {
            'tokenId': ?0,
            'method': ?1,
            'targetUrl': ?2
        }
        """,
            sort = "{ 'analyzedTime' : -1 }"
    )
    Optional<AnalyzeSpecDocument> findLatest(
            UUID tokenId,
            String method,
            String targetUrl
    );

    @Query(
            value = """
        {
            'tokenId': ?0,
            'method': ?1,
            'targetUrl': ?2,
            'isBaseline': true
        }
        """,
            sort = "{ 'analyzedTime' : -1 }"
    )
    Optional<AnalyzeSpecDocument> findLatestWithBaseline(
            UUID tokenId,
            String method,
            String targetUrl
    );

}
