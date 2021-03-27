package com.example.wbdvsp2101harrisonjwongserverjava.repositories;

import com.example.wbdvsp2101harrisonjwongserverjava.models.Widget;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface WidgetRepository extends CrudRepository<Widget, Integer> {

  @Query(value="SELECT * FROM widgets WHERE topic_id=:tid", nativeQuery = true)
  List<Widget> findAllByTopicId(String tid);

  @Query(value="SELECT * FROM widgets", nativeQuery = true)
  List<Widget> findAllWidgets();
}
