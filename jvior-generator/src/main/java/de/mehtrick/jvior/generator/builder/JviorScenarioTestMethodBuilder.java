package de.mehtrick.jvior.generator.builder;

import java.util.List;
import java.util.stream.Collectors;

import javax.lang.model.element.Modifier;

import org.junit.Test;

import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.MethodSpec.Builder;

import de.mehtrick.jvior.parser.modell.Jvior;
import de.mehtrick.jvior.parser.modell.JviorScenario;
import de.mehtrick.jvior.parser.modell.JviorStatement;

public class JviorScenarioTestMethodBuilder {

	public static List<MethodSpec> build(Jvior jvior) {
		return jvior.getScenarios().stream().map(JviorScenarioTestMethodBuilder::parseJviroScenario)
				.collect(Collectors.toList());
	}

	private static MethodSpec parseJviroScenario(JviorScenario scenario) {
		Builder main = MethodSpec.methodBuilder(scenario.getNameFormatted()).addAnnotation(Test.class)
				.addModifiers(Modifier.PUBLIC).addJavadoc(scenario.getName());

		scenario.getGiven().stream()
				.forEach(given -> main.addStatement(JviorScenarioTestMethodBuilder.parseStatement(given)));
		scenario.getWhen().stream()
				.forEach(when -> main.addStatement(JviorScenarioTestMethodBuilder.parseStatement(when)));

		scenario.getThen().stream()
				.forEach(then -> main.addStatement(JviorScenarioTestMethodBuilder.parseStatement(then)));

		return main.build();
	}

	private static String parseStatement(JviorStatement statement) {
		String parameters = statement.getParameters().stream().map(p -> String.format("\"%s\"", p))
				.collect(Collectors.joining(", "));
		return statement.getStatementWithoutParameters() + "(" + parameters + ")";
	}

}