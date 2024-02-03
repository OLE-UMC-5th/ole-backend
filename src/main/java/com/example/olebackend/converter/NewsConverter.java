package com.example.olebackend.converter;

import com.example.olebackend.domain.File;
import com.example.olebackend.domain.News;
import com.example.olebackend.web.dto.NewsResponse;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class NewsConverter {

    public static NewsResponse.getNewsPreviewDTO toNewsPreviewDTO(News news) {
        List<File> fileList = news.getFileList();

        File file = null;
        if (fileList != null) {
            file = fileList.stream()
                    .filter(File::isRepresent)
                    .findFirst()
                    .orElse(null);
        }

        return NewsResponse.getNewsPreviewDTO.builder()
                .id(news.getId())
                .createdAt(news.getCreatedAt())
                .category(news.getCategory().toString())
                .title(news.getTitle())
                .content(news.getContent())
                .author(news.getAuthor())
                .filePath(file != null ? file.getPath() : null)
                .build();
    }

    public static NewsResponse.getNewsListDTO toNewsListDTO(Page<News> newsList) {
        List<NewsResponse.getNewsPreviewDTO> newsPreviewDTOList = newsList.stream()
                .map(NewsConverter::toNewsPreviewDTO).collect(Collectors.toList());

        return NewsResponse.getNewsListDTO.builder()
                .newsList(newsPreviewDTOList)
                .isLast(newsList.isLast())
                .isFirst(newsList.isFirst())
                .totalPage(newsList.getTotalPages())
                .totalElements(newsList.getTotalElements())
                .listSize(newsPreviewDTOList.size())
                .build();
    }

    public static NewsResponse.getNewsFileDTO toNewsFileDTO(File file) {
        return NewsResponse.getNewsFileDTO.builder()
                .filePath(file.getPath())
                .build();
    }

    public static NewsResponse.getNewsDetailDTO toNewsDetailDTO(Optional<News> news) {
        List<File> fileList = news.get().getFileList();

        List<NewsResponse.getNewsFileDTO> newsFileDTOList = fileList.stream()
                .map(NewsConverter::toNewsFileDTO).collect(Collectors.toList());

        return NewsResponse.getNewsDetailDTO.builder()
                .title(news.get().getTitle())
                .content(news.get().getContent())
                .category(news.get().getCategory().toString())
                .author(news.get().getAuthor())
                .createdAt(news.get().getCreatedAt())
                .fileList(newsFileDTOList)
                .views(news.get().getViews())
                .build();


    }
}
