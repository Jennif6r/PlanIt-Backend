package builder;

import java.time.LocalDate;
import java.util.UUID;

import main.Lecture;

public class LectureBuilder {
	private String id;
	private String module;
	private LocalDate start;
	private LocalDate end;
	private String place;
	private boolean weekly;

	public LectureBuilder() {
		this.id = UUID.randomUUID().toString();
	}
	
	public LectureBuilder module(String module) {
		this.module = module;
		return this;
	}

	public LectureBuilder start(LocalDate start) {
		this.start = start;
		return this;
	}

	public LectureBuilder end(LocalDate end) {
		this.end = end;
		return this;
	}

	public LectureBuilder place(String place) {
		this.place = place;
		return this;
	}

	public LectureBuilder weekly(boolean weekly) {
		this.weekly = weekly;
		return this;
	}

	public Lecture build() {
		return new Lecture(id, module, start, end, place, weekly);
	}
}
