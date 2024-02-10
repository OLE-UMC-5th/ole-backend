package com.example.olebackend.service;

import com.example.olebackend.apiPayLoad.exception.GeneralException;
import com.example.olebackend.domain.Lesson;
import com.example.olebackend.domain.common.BaseEntity;
import com.example.olebackend.repository.CategoryRepository;
import com.example.olebackend.repository.LessonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.example.olebackend.apiPayLoad.code.status.ErrorStatus.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LessonService {

    private final LessonRepository lessonRepository;
    private final CategoryRepository categoryRepository;

    @Transactional
    public Optional<Lesson> getLessonDetail(Long lessonId) {
        Optional<Lesson> lesson = lessonRepository.findById(lessonId);
        lesson.ifPresent(Lesson::incrementViews);
        lessonRepository.save(lesson.get());
        if (!lessonRepository.existsById(lessonId)) {
            throw new GeneralException(LESSON_NOT_FOUND);
        }
        return lesson;
    }

    public Page<Lesson> getLessonListByCategory(Long categoryId, Integer page) {

        Page<Lesson> lessonList = lessonRepository.findBySubCategoryCategoryId(categoryId, PageRequest.of(page - 1, 10));

        // 카테고리 자체가 없을 때
        if (!categoryRepository.existsById(categoryId)) {
            throw new GeneralException(CATEGORY_NOT_FOUND);
        }

        // 해당 카테고리에 존재하는 교육이 없을 때
        if (lessonList.getTotalPages() == 0) {
            throw new GeneralException(LESSON_NOT_FOUND);
        }

        // 전체 페이지 수 이상의 값을 입력했을 때
        if (page > lessonList.getTotalPages()) {
            throw new GeneralException(PAGE_NOT_FOUND);
        }

        return lessonList;
    }


    public Page<Lesson> getLessonListBySpecification(Long categoryId, Specification<Lesson> spec, Integer page) {
        Page<Lesson> lessonList = lessonRepository.findBySubCategoryCategoryId(categoryId, PageRequest.of(page - 1, 10));

        Page<Lesson> lessonListByKeyWord = lessonRepository.findAll(spec.and((root, query, criteriaBuilder) -> criteriaBuilder.isTrue(criteriaBuilder.literal(true))), lessonList.getPageable());

        // 카테고리 자체가 없을 때
        if (!categoryRepository.existsById(categoryId)) {
            throw new GeneralException(CATEGORY_NOT_FOUND);
        }

        // 해당 카테고리에 존재하는 교육이 없을 때
        if (lessonListByKeyWord.getTotalPages() == 0) {
            throw new GeneralException(LESSON_NOT_FOUND);
        }

        // 전체 페이지 수 이상의 값을 입력했을 때
        if (page > lessonList.getTotalPages()) {
            throw new GeneralException(PAGE_NOT_FOUND);
        }
        return lessonListByKeyWord;
    }

    public List<Lesson> getLessonListByOrderCriteria(String orderCriteria) {

        Class<?> currentClass = Lesson.class;
        while (currentClass != null) {
            try {
                currentClass.getDeclaredField(orderCriteria);
                break;
            } catch (NoSuchFieldException ignored) {
                currentClass = currentClass.getSuperclass();
            }
        }

        if (currentClass == null) {
            throw new GeneralException(ORDER_CRITERIA_INVALID);
        }

        List<Lesson> lessonList = lessonRepository.findAll(Sort.by(Sort.Direction.DESC, orderCriteria));

        return lessonList;
    }

    public Page<Lesson> getLessonListBySearch(Specification<Lesson> spec, Integer page) {

        Page<Lesson> lessonList = lessonRepository.findAll(spec, PageRequest.of(page - 1, 10));
        return lessonList;
    }
}
