package com.ideabaker.samples.stream.kafkaspark.datastore.model

import com.fasterxml.jackson.annotation.JsonAlias
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.TypeAlias
import org.springframework.data.mongodb.core.mapping.Document

@Document
@TypeAlias("rsvps")
data class MeetupRSVP (
    @Id
    val id: String?,

    @JsonAlias("rsvp_id")
    val rsvpId: Int,

    val venue: Venue?,
    val visibility: String,
    val response: String,

    @JsonAlias("guests")
    val numberOfGuests: Int,

    val member: Member?,
    val mtime: Long,
    val event: Event?,
    val group: Group?
)

data class Venue (
    @JsonAlias("venue_id")
    val id: Int,

    @JsonAlias("venue_name")
    val name: String,

    val lat: Double,
    val lon: Double
)

data class Member (
    @JsonAlias("member_id")
    val id: Int,

    val photo: String?,

    @JsonAlias("member_name")
    val name: String,

    @JsonAlias("other_services")
    val services: Map<String, MemberService>?
)

data class MemberService (
    val identifier: String
)

data class Event (
    @JsonAlias("event_id")
    val id: String,

    @JsonAlias("event_name")
    val name: String,

    val time: Long,

    @JsonAlias("event_url")
    val url: String
)

data class Group (
    @JsonAlias("group_id")
    val id: Int,

    @JsonAlias("group_city")
    val city: String,

    @JsonAlias("group_country")
    val country: String,

    @JsonAlias("group_state")
    val state: String?,

    @JsonAlias("group_name")
    val name: String,

    @JsonAlias("group_lat")
    val lat: Double,

    @JsonAlias("group_lon")
    val lon: Double,

    @JsonAlias("group_urlname")
    val urlName: String,

    @JsonAlias("group_topics")
    val topics: List<Topic>
)

data class Topic (
    val urlkey: String,

    @JsonAlias("topic_name")
    val name: String
)
