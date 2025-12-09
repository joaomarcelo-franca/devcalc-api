package com.devcalc;

import io.javalin.Javalin;

public class App {
    public static void main(String[] args) {

        CalculatorService service = new CalculatorService();

        Javalin app = Javalin.create().start(7000);

        app.get("/add", ctx -> {
            double a = Double.parseDouble(ctx.queryParam("a"));
            double b = Double.parseDouble(ctx.queryParam("b"));
            ctx.json(service.add(a, b));
        });

        app.get("/subtract", ctx -> {
            double a = Double.parseDouble(ctx.queryParam("a"));
            double b = Double.parseDouble(ctx.queryParam("b"));
            ctx.json(service.subtract(a, b));
        });

        app.get("/multiply", ctx -> {
            double a = Double.parseDouble(ctx.queryParam("a"));
            double b = Double.parseDouble(ctx.queryParam("b"));
            ctx.json(service.multiply(a, b));
        });

        app.get("/divide", ctx -> {
            double a = Double.parseDouble(ctx.queryParam("a"));
            double b = Double.parseDouble(ctx.queryParam("b"));
            try {
                ctx.json(service.divide(a, b));
            } catch (ArithmeticException exception){
                ctx.status(400).result(exception.getMessage());
            }
        });
    }
}
