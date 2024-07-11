package com.restaurant.management.Controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.awt.Font;

import org.jfree.chart.ChartColor;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;

import org.jfree.chart.axis.DateAxis;

import org.jfree.chart.plot.PlotOrientation;

import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.chart.ChartUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.restaurant.management.Services.*;
import com.restaurant.management.Supporting_Entity.Core.Order;
import com.restaurant.management.Supporting_Entity.Product.*;
import com.restaurant.management.Supporting_Services.Profit_Calculation;
import com.restaurant.management.Supporting_Services.Revenue_Calculation;


import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class OwnerController {
    
   @Autowired
   private OwnerServices ownerService;

public OwnerController(OwnerServices ownerService) {
    super();
    this.ownerService = ownerService;
}

   @GetMapping("/owner-dash")
   public String owner_Dashboard()
   {    
    return "owner-dash";
   }

   @GetMapping("/menu/view")
   public String viewMenu(Model model)
   {    
      List<Dish> menuDishes = ownerService.getMenuDishes();
      List<Drink> menuDrinks = ownerService.getMenuDrinks();

      model.addAttribute("dishes", menuDishes);
      model.addAttribute("drinks", menuDrinks);
    return "viewMenu";
   }
    
   @GetMapping("/owner/gst/dates")
   public String getGstDates()
   {
    return "gst";
   }

   @PostMapping("/owner/gst/dates/display")
   public String displayGst(@RequestParam("startDate") String start,@RequestParam("endDate") String end,Model model) throws ParseException
   {

    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    Date startDate = formatter.parse(start);
    Date endDate = formatter.parse(end);
    
    System.out.println("\n\n" + start  + "\n\n To" + end);

    List<Float> allGst = ownerService.getAllGSTforDates(startDate, endDate);

    float total = 0;

    for(int i = 0; i < allGst.size();i++)
    {

        total = total + allGst.get(i);
    }

    model.addAttribute("result", total);
    return "displayGst";
   }





   @GetMapping("/owner/profit/dates")
   public String getProfitDates()
   {
    return "profit";
   }

   @PostMapping("/owner/profit/dates/display")
   public String displayProfit(@RequestParam("startDate") String start,@RequestParam("endDate") String end,Model model) throws ParseException
   {

    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    Date startDate = formatter.parse(start);
    Date endDate = formatter.parse(end);
    
    System.out.println("\n\n" + start  + "\n\n To" + end);

    List<Order> allOrders = ownerService.getAllOrderforDates(startDate, endDate);
    Profit_Calculation calc = new Profit_Calculation();

    float totalProfit = 0;

    for(int i = 0;i<allOrders.size();i++)
    {
      totalProfit += calc.calculateProfit(allOrders.get(i));
    }

    model.addAttribute("result", totalProfit);
    return "profitDisplay";
   }



   @GetMapping("/owner/rev/dates")
   public String getRevDates()
   {
    return "rev";
   }

   @PostMapping("/owner/rev/dates/display")
   public String displayRev(@RequestParam("startDate") String start,@RequestParam("endDate") String end,Model model) throws ParseException
   {

    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    Date startDate = formatter.parse(start);
    Date endDate = formatter.parse(end);
    
    System.out.println("\n\n" + start  + "\n\n To" + end);

    List<Order> allOrders = ownerService.getAllOrderforDates(startDate, endDate);
    Revenue_Calculation calc = new Revenue_Calculation();

    float totalRev = calc.calculateRevenue(allOrders);

    model.addAttribute("result", totalRev);
    return "revDisplay";
   }


   @GetMapping("/owner/hot/dates")
   public String getHotDates()
   {
    return "hotDish";
   }

   @PostMapping("/owner/hot/dates/display")
   public String displayHotDish(@RequestParam("startDate") String start,@RequestParam("endDate") String end,Model model) throws ParseException
   {

    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    Date startDate = formatter.parse(start);
    Date endDate = formatter.parse(end);
    
    System.out.println("\n\n" + start  + "\n\n To" + end);

    List<Object[]> hotDishs = ownerService.getHotFavDish(startDate, endDate);


    System.out.println("\n\n" + hotDishs.get(0)  + "\n\n To" + hotDishs.size());

    Long dishId = (long) 0;
    Long quantity = (long) 0;
    for (Object[] result : hotDishs) 
   
       { dishId = (Long) result[0];
        quantity = (Long) result[1];}

    String hot = ownerService.getDishById(dishId).getName();
    model.addAttribute("result",hot);
    model.addAttribute("freq", quantity );

    return "hotDisplay";
   }


   @GetMapping("owner/rev-graph")
   public String graphs()
   {
    return "graphs";
   }

   @PostMapping("/rev-graph/display")
   public String displayGraph(@RequestParam("graph") String graph)
   {
    if(graph.equals("Bar"))
    {
        return "redirect:/owner/revenue/bar-graph";
    }
     else if(graph.equals("Pie"))
    {
        {
            return "redirect:/owner/revenue/pie-chart";
        }
    }
    else 
    {
        return "redirect:/owner/revenue/line-graph";
    }
    }


   ////////////////////////////


   @GetMapping("/owner/revenue/bar-graph")
    public String generateRevenueGraph(Model model, HttpServletResponse response) throws IOException
    {                                                                   

        // retrieve the order dishes data from the order repository

        LocalDate date = LocalDate.now();

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for(int i = 0; i < 7;i++)
        {
         date = date.minusDays(1);
         Date d = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());


        
        List<Order> orders = ownerService.getOrderByDate(d);
   
        // create a dataset for the revenue bar graph
        

        Revenue_Calculation calc = new Revenue_Calculation();

        float revenue = calc.calculateRevenue(orders);

        dataset.addValue(revenue, "Revenue", d);

        System.out.println("\n" + revenue + "\n");
        }
        // create the revenue bar chart using JFreeChart
        JFreeChart chart = ChartFactory.createBarChart(
            "Revenue Bar Graph",   // chart title
            "Week Days",            // category axis label
            "Revenue",              // value axis label
            dataset,                // dataset
            PlotOrientation.VERTICAL,
            true,                   // legend
            true,                   // tooltips
            false                   // urls
        );
        chart.setBackgroundPaint(ChartColor.white);

        // set the font for the chart
        Font titleFont = new Font("Times New Roman", Font.BOLD, 16);
        chart.getTitle().setFont(titleFont);

        Font labelFont = new Font("Times New Roman", Font.BOLD, 12);
        chart.getCategoryPlot().getDomainAxis().setLabelFont(labelFont);
        chart.getCategoryPlot().getRangeAxis().setLabelFont(labelFont);

        // generate the chart image
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ChartUtils.writeChartAsPNG(baos, chart, 2000, 1000);
        byte[] imageData = baos.toByteArray();

        // set the chart image data as a model attribute
        model.addAttribute("imageData", imageData);


    response.setContentType("image/png");
    ServletOutputStream outputStream = response.getOutputStream();
    outputStream.write(imageData);
    outputStream.flush();
    outputStream.close();

        // return the Thymeleaf template for displaying the chart
        return "null";
    }

   

    @GetMapping("/owner/revenue/pie-chart")
    public String generateRevenuePieChart(Model model, HttpServletResponse response) throws IOException {

    // retrieve the order dishes data from the order repository
    LocalDate date = LocalDate.now();
    DefaultPieDataset dataset = new DefaultPieDataset();
    
    for(int i = 0; i < 7;i++)
    {
        date = date.minusDays(1);
        Date d = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());

        List<Order> orders = ownerService.getOrderByDate(d);

        // create a dataset for the revenue pie chart
        Revenue_Calculation calc = new Revenue_Calculation();
        float revenue = calc.calculateRevenue(orders);
        dataset.setValue(d.toString(), revenue);
        System.out.println("\n" + revenue + "\n");
    }

    // create the revenue pie chart using JFreeChart
    JFreeChart chart = ChartFactory.createPieChart(
            "Revenue Pie Chart",   // chart title
            dataset,                // dataset
            true,                   // legend
            true,                   // tooltips
            false                   // urls
    );
    chart.setBackgroundPaint(ChartColor.white);

    // set the font for the chart
    Font titleFont = new Font("Times New Roman", Font.BOLD, 16);
    chart.getTitle().setFont(titleFont);

    Font labelFont = new Font("Times New Roman", Font.BOLD, 12);
    chart.getLegend().setItemFont(labelFont);
    //chart.getPlot().setLabelFont(labelFont);

    // generate the chart image
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    ChartUtils.writeChartAsPNG(baos, chart, 800, 600);
    byte[] imageData = baos.toByteArray();


    response.setContentType("image/png");
    ServletOutputStream outputStream = response.getOutputStream();
    outputStream.write(imageData);
    outputStream.flush();
    outputStream.close();

    // set the chart image data as a model attribute
    model.addAttribute("imageData", imageData);

    // return the Thymeleaf template for displaying the chart
    return "revenuePieChart";
}

@GetMapping("/owner/revenue/line-graph")
public String generateRevenueLineGraph(Model model, HttpServletResponse response) throws IOException {

    // retrieve the order dishes data from the order repository
    LocalDate date = LocalDate.now();
    XYSeries series = new XYSeries("Revenue");
    
    for(int i = 0; i < 7; i++) {
        date = date.minusDays(1);
        Date d = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());

        List<Order> orders = ownerService.getOrderByDate(d);

        // create a dataset for the revenue line graph
        Revenue_Calculation calc = new Revenue_Calculation();
        float revenue = calc.calculateRevenue(orders);
        series.add(d.getTime(), revenue);
        System.out.println("\n" + revenue + "\n");
    }

    // create the revenue line graph using JFreeChart
    XYSeriesCollection dataset = new XYSeriesCollection(series);
    JFreeChart chart = ChartFactory.createXYLineChart(
            "Revenue Line Graph",   // chart title
            "Date",                 // x-axis label
            "Revenue",              // y-axis label
            dataset,                // dataset
            PlotOrientation.VERTICAL,
            true,                   // legend
            true,                   // tooltips
            false                   // urls
    );
    // Set custom DateAxis for the X-axis
    DateAxis dateAxis = new DateAxis("Date");
    dateAxis.setDateFormatOverride(new SimpleDateFormat("MM/dd/yyyy")); // Set your desired date format
    chart.getXYPlot().setDomainAxis(dateAxis);

    chart.setBackgroundPaint(ChartColor.white);

    // set the font for the chart
    Font titleFont = new Font("Times New Roman", Font.BOLD, 16);
    chart.getTitle().setFont(titleFont);

    Font labelFont = new Font("Times New Roman", Font.BOLD, 12);
    chart.getLegend().setItemFont(labelFont);
    chart.getXYPlot().getDomainAxis().setLabelFont(labelFont);
    chart.getXYPlot().getRangeAxis().setLabelFont(labelFont);

    // generate the chart image
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    ChartUtils.writeChartAsPNG(baos, chart, 800, 600);
    byte[] imageData = baos.toByteArray();

    response.setContentType("image/png");
    ServletOutputStream outputStream = response.getOutputStream();
    outputStream.write(imageData);
    outputStream.flush();
    outputStream.close();

    // set the chart image data as a model attribute
    model.addAttribute("imageData", imageData);

    // return the Thymeleaf template for displaying the chart
    return "null";
}

}

