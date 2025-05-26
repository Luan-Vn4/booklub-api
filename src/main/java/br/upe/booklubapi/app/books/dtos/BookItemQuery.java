package br.upe.booklubapi.app.books.dtos;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookItemQuery {

    public String intitle;
    public String inauthor;
    public String inpublisher;
    public String subject;
    public String isbn;

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        if (intitle != null && !intitle.isBlank()) {
            builder.append("intitle:").append(intitle.trim()).append("+");
        }
        if (inauthor != null && !inauthor.isBlank()) {
            builder.append("inauthor:").append(inauthor.trim()).append("+");
        }
        if (inpublisher != null && !inpublisher.isBlank()) {
            builder.append("inpublisher:").append(inpublisher.trim()).append("+");
        }
        if (subject != null && !subject.isBlank()) {
            builder.append("subject:").append(subject.trim()).append("+");
        }
        if (isbn != null && !isbn.isBlank()) {
            builder.append("isbn:").append(isbn.trim()).append("+");
        }

        if (!builder.isEmpty() && builder.charAt(builder.length() - 1) == '+') {
            builder.deleteCharAt(builder.length() - 1);
        }

        return builder.toString();
    }
}
