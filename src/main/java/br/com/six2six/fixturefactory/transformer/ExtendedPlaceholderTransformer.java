package br.com.six2six.fixturefactory.transformer;

import br.com.six2six.fixturefactory.util.ReflectionUtils;

public class ExtendedPlaceholderTransformer implements Transformer {

	private static final String PATTERN = "\\#\\{[a-zA-z0-9_]*\\}$";

	private final Object result;

	public ExtendedPlaceholderTransformer(final Object result) {
		this.result = result;
	}

	public <T> T transform(Object value, Class<T> type) {
		String expression = (String) value;
		if (expression.matches(PATTERN)) {
			String propertyName = expression.replace("#", "").replace("{", "").replace("}", "");
			return type.cast(getValue(propertyName));
		} else {
			throw new IllegalArgumentException(String.format("invalid expression found in '%s' - please use this kind of expression: '#{propertyName}' instead", expression));
		}
	}

	public boolean accepts(Object value, Class<?> type) {
		return value instanceof String && type != String.class && ((String) value).matches(PATTERN);
	}

	@SuppressWarnings("unchecked")
	protected <T> T getValue(String propertyName) {
		return (T) ReflectionUtils.invokeRecursiveGetter(result, propertyName);
	}

}
