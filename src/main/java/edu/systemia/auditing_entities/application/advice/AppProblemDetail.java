package edu.systemia.auditing_entities.application.advice;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

@Setter
@Getter
public class AppProblemDetail extends ProblemDetail {

	private String language;
	private String titleStatus;

	public AppProblemDetail(int rawStatusCode) {
		super(rawStatusCode);
	}

	public static AppProblemDetail forStatus(int status) {
		return new AppProblemDetail(status);
	}

	public static AppProblemDetail forStatusAndDetail(HttpStatusCode status, @Nullable String detail) {
		Assert.notNull(status, "HttpStatusCode is required");
		AppProblemDetail problemDetail = forStatus(status.value());
		problemDetail.setDetail(detail);
		return problemDetail;
	}
}
