package com.example.wbdvsp2101harrisonjwongserverjava.services;

import com.example.wbdvsp2101harrisonjwongserverjava.models.Widget;
import com.example.wbdvsp2101harrisonjwongserverjava.repositories.WidgetRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WidgetService {

  @Autowired
  private WidgetRepository widgetRepository;

//  private static final String TEST_TOPIC_ID = "605029b9705e6e00173760a6";
//  private static final String LOREM_IPSUM = "Lorem ipsum dolor sit amet, "
//      + "consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore "
//      + "et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation "
//      + "ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor "
//      + "in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. "
//      + "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt "
//      + "mollit anim id est laborum.";


  // Creates a new Widget instance and add it to the existing collection of widgets
  // for a topic whose ID is tid. Returns new widget with a unique identifier
  public Widget createWidget(String tid, Widget widget) {
    widget.setTopicId(tid);
    return widgetRepository.save(widget);
  }

  // Returns collection of all widgets for a topic whose ID is tid
  public List<Widget> findWidgetsForTopic(String tid) {
    return widgetRepository.findAllByTopicId(tid);
  }

  // Updates widget whose id is wid encoded as JSON in HTTP body.
  // Returns 1 if successful, 0 otherwise
  public int updateWidget(String wid, Widget widget) {
    Widget originalWidget = findWidgetById(wid);
    if (originalWidget == null) {
      return 0;
    }
    //Fields used: id, type, text, size, url, width, height, value
    if (widget.getTopicId() != null) {
      originalWidget.setTopicId(widget.getTopicId());
    }
    if (widget.getType() != null) {
      originalWidget.setType(widget.getType());
    }
    if (widget.getText() != null) {
      originalWidget.setText(widget.getText());
    }
    if (widget.getSize() != null) {
      originalWidget.setSize(widget.getSize());
    }
    if (widget.getUrl() != null) {
      originalWidget.setUrl(widget.getUrl());
    }
    if (widget.getWidth() != null) {
      originalWidget.setWidth(widget.getWidth());
    }
    if (widget.getHeight() != null) {
      originalWidget.setHeight(widget.getHeight());
    }
    if (widget.getValue() != null) {
      originalWidget.setValue(widget.getValue());
    }
    widgetRepository.save(originalWidget);
    return 1;
  }

  // Removes widget whose id is wid. Returns 1 if successful, 0 otherwise
  public int deleteWidget(String wid) {
    Integer theId = parseIdHelper(wid);
    if (theId == null) {
      return 0;
    }
    widgetRepository.deleteById(theId);
    return 1;
  }

  // Returns collection of all widgets (optional)
  public List<Widget> findAllWidgets() {
    return widgetRepository.findAllWidgets();
  }

  // Returns a single widget instance whose id is equal to wid (optional)
  public Widget findWidgetById(String wid) {
    Integer id = parseIdHelper(wid);
    if (id == null) {
      return null;
    }
    Optional<Widget> widget = widgetRepository.findById(id);
    return widget.orElse(null);
  }

  private Integer parseIdHelper(String id) {
    int parsedId;
    try {
      parsedId = Integer.parseInt(id);
    } catch (NumberFormatException e) {
      return null;
    }
    return parsedId;
  }
}
