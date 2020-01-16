package com.kulagin.controller;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class BookDTO {
    Long id;
    String name;
    String author;
}