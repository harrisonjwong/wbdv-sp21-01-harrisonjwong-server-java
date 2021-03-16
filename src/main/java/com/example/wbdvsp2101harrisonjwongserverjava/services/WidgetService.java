package com.example.wbdvsp2101harrisonjwongserverjava.services;

import com.example.wbdvsp2101harrisonjwongserverjava.models.Widget;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class WidgetService {

  private List<Widget> widgets;
  private static final String TEST_TOPIC_ID = "605029b9705e6e00173760a6";
  private static final String LOREM_IPSUM = "Lorem ipsum dolor sit amet, "
      + "consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore "
      + "et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation "
      + "ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor "
      + "in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. "
      + "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt "
      + "mollit anim id est laborum.";

  public WidgetService() {
    widgets = new ArrayList<>();
    Widget w1 = new Widget();
    w1.setId(Math.abs(new Random().nextInt()));
    w1.setType("HEADING");
    w1.setText("Heading not being edited (default)");
    w1.setSize(1);
    w1.setTopicId(TEST_TOPIC_ID);
    widgets.add(w1);
    Widget w2 = new Widget();
    w2.setId(Math.abs(new Random().nextInt()));
    w2.setType("HEADING");
    w2.setSize(4);
    w2.setText("Heading being edited");
    w2.setTopicId(TEST_TOPIC_ID);
    widgets.add(w2);
    Widget w3 = new Widget();
    w3.setId(Math.abs(new Random().nextInt()));
    w3.setType("PARAGRAPH");
    w3.setSize(1);
    w3.setText("Paragraph not being edited (default) " + LOREM_IPSUM);
    w3.setTopicId(TEST_TOPIC_ID);
    widgets.add(w3);
    Widget w4 = new Widget();
    w4.setId(Math.abs(new Random().nextInt()));
    w4.setType("PARAGRAPH");
    w4.setSize(1);
    w4.setText("Paragraph being edited " + LOREM_IPSUM);
    w4.setTopicId(TEST_TOPIC_ID);
    widgets.add(w4);
  }

  // Creates a new Widget instance and add it to the existing collection of widgets
  // for a topic whose ID is tid. Returns new widget with a unique identifier
  public Widget createWidget(String tid, Widget widget) {
    widget.setTopicId(tid);
    int randomId = Math.abs(new Random().nextInt());
    widget.setId(randomId);
    widgets.add(widget);
    return widget;
  }

  // Returns collection of all widgets for a topic whose ID is tid
  public List<Widget> findWidgetsForTopic(String tid) {
    return widgets.stream().filter(
        w -> w.getTopicId().equals(tid)).collect(Collectors.toList());
  }

  // Updates widget whose id is wid encoded as JSON in HTTP body.
  // Returns 1 if successful, 0 otherwise
  public int updateWidget(String wid, Widget widget) {
    for (int i = 0; i < widgets.size(); i++) {
      Widget w = widgets.get(i);
      if (w.getId().equals(parseIdHelper(wid))) {
        widgets.set(i, widget);
        return 1;
      }
    }
    return 0;
  }

  // Removes widget whose id is wid. Returns 1 if successful, 0 otherwise
  public int deleteWidget(String wid) {
    int index = -1;
    for (int i = 0; i < widgets.size(); i++) {
      Widget w = widgets.get(i);
      if (w.getId().equals(parseIdHelper(wid))) {
        index = i;
      }
    }
    if (index >= 0) {
      widgets.remove(index);
      return 1;
    }
    return 0;
  }

  // Returns collection of all widgets (optional)
  public List<Widget> findAllWidgets() {
    return widgets;
  }

  // Returns a single widget instance whose id is equal to wid (optional)
  public Widget findWidgetById(String wid) {
    return widgets.stream().filter(
        w -> w.getId().equals(parseIdHelper(wid))
    ).findFirst().orElse(null);
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
