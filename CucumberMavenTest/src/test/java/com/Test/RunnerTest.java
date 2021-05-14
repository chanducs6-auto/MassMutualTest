package com.Test;

import org.junit.runner.RunWith;
import cucumber.api.junit.Cucumber;


@RunWith(Cucumber.class)
@Cucumber.Options(features="FeatureFiles\\balanceCheck.feature",
	glue= {"com.stepDef"},
	tags={"@BalanceCheck"},
	format = {"pretty", "html:target/cucumber-htmlreport","json-pretty:target/cucumber-report.json"},
	monochrome=true
	)
public class RunnerTest {

}
