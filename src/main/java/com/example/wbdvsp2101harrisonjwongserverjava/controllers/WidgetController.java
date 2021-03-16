package com.example.wbdvsp2101harrisonjwongserverjava.controllers;

import com.example.wbdvsp2101harrisonjwongserverjava.models.Widget;
import com.example.wbdvsp2101harrisonjwongserverjava.services.WidgetService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class WidgetController {

  @Autowired
  private WidgetService widgetService;

  // parses Widget JSON object from HTTP body encoded as JSON string.
  // Uses WidgetService to create a new Widget instance and add it to the
  // existing collection of widgets for a topic whose ID is tid.
  // Returns the new widget with a unique identifier
  @PostMapping("/api/topics/{tid}/widgets")
  public Widget createWidget(
      @PathVariable("tid") String tid,
      @RequestBody Widget widget) {
    return widgetService.createWidget(tid, widget);
  }

  // uses WidgetService to retrieve collection of all widgets and returns a
  // string encoded as a JSON array for a topic whose ID is tid
  @GetMapping("/api/topics/{tid}/widgets")
  public List<Widget> findWidgetsForTopic(@PathVariable("tid") String tid) {
    return widgetService.findWidgetsForTopic(tid);
  }

  // parses Widget JSON object from HTTP body encoded as JSON string.
  // Uses WidgetService to find widget instance whose id is equal to wid
  // and update the fields to be the new values in widget parameter.
  // Returns 1 if successful, 0 otherwise.
  @PutMapping("/api/widgets/{wid}")
  public Integer updateWidget(
      @PathVariable("wid") String wid,
      @RequestBody Widget widget) {
    return widgetService.updateWidget(wid, widget);
  }

  // uses WidgetService to remove widget whose id is wid. Returns 1 if successful, 0 otherwise.
  @DeleteMapping("/api/widgets/{wid}")
  public Integer deleteWidget(@PathVariable("wid") String wid) {
    return widgetService.deleteWidget(wid);
  }

  // uses WidgetService to retrieve collection of all widgets and returns a
  // string encoded as a JSON array. (optional)
  @GetMapping("/api/widgets")
  public List<Widget> findAllWidgets() {
    return widgetService.findAllWidgets();
  }

  @GetMapping("/api/widgets/{wid}")
  public Widget findWidgetById(@PathVariable("wid") String wid) {
    return widgetService.findWidgetById(wid);
  }
}
