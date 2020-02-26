package com.ashessin.cs474.hw1.generator.behavioral;

import ch.qos.logback.classic.Logger;
import com.ashessin.cs474.hw1.generator.DpArrayList;
import com.ashessin.cs474.hw1.generator.DpSource;
import org.slf4j.LoggerFactory;
import picocli.CommandLine;

import java.util.concurrent.Callable;

@CommandLine.Command(name = "interpreter", version = "jdt-dpgen 0.1",
	description = "Generates Interpreter behavioral design pattern. " +
				  "Given a language, define a representation for its grammar along with an " +
				  "interpreter that uses the representation to interpret sentences in the language.",
	mixinStandardHelpOptions = true,
	showDefaultValues = true,
	sortOptions = false
)
public class InterpreterQ implements Callable<DpArrayList<DpSource>> {

	private static final Logger log = (Logger) LoggerFactory.getLogger(InterpreterQ.class);
	private static final String PACKAGE_NAME = "com.gof.behavioral.interpreter";

	@CommandLine.ArgGroup(exclusive = false)
	InputGroup inputGroup;

	@CommandLine.Option(order = Integer.MIN_VALUE, required = true, arity = "1", names = {"-p", "--packageName"},
		defaultValue = PACKAGE_NAME)
	String packageName;

	public static void main(String[] args) {
		System.setProperty("picocli.usage.width", "auto");
		int exitCode = new CommandLine(new InterpreterQ()).execute(args);
		System.exit(exitCode);
	}

	@Override
	public DpArrayList<DpSource> call() throws Exception {
		String contextName = InputGroup.contextName;
		String abstractExpressionName = InputGroup.abstractExpressionName;
		String terminalExpressionName = InputGroup.terminalExpressionName;
		String orTerminalExpressionName = InputGroup.OR_TERMINAL_EXPRESSION_NAME;
		String andTerminalExpressionName = InputGroup.AND_TERMINAL_EXPRESSION_NAME;

		// TODO: Add input validations

		log.info("Generating representation for design pattern sources.");
		return new InterpreterGen(packageName, contextName, abstractExpressionName,
			terminalExpressionName, orTerminalExpressionName, andTerminalExpressionName).main();
	}

	static class InputGroup {

		private static final String CONTEXT_NAME = "Context";
		private static final String ABSTRACT_EXPRESSION_NAME = "AbstractExpression";
		private static final String TERMINAL_EXPRESSION_NAME = "TerminalExpression";
		private static final String OR_TERMINAL_EXPRESSION_NAME = "OrTerminalExpression";
		private static final String AND_TERMINAL_EXPRESSION_NAME = "AndTerminalExpression";


		@CommandLine.Parameters(index = "0", paramLabel = "ContextName")
		static String contextName = CONTEXT_NAME;
		@CommandLine.Parameters(index = "1", paramLabel = "AbstractExpressionName",
			description = "The AbstractExpresion defines interface for interpretation.")
		static String abstractExpressionName = ABSTRACT_EXPRESSION_NAME;
		@CommandLine.Parameters(index = "2", paramLabel = "TerminalExpressionName",
			description = "The TerminalExpresion implements the AbstractExpression " +
						  "for literal symbols in the grammar.")
		static String terminalExpressionName = TERMINAL_EXPRESSION_NAME;
		@CommandLine.Parameters(index = "3", paramLabel = "OrTerminalExpressionName")
		static String orTerminalExpressionName = OR_TERMINAL_EXPRESSION_NAME;
		@CommandLine.Parameters(index = "4", paramLabel = "AndTerminalExpressionName")
		static String andTerminalExpressionName = AND_TERMINAL_EXPRESSION_NAME;

		private InputGroup() {
		}
	}
}
