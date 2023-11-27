package ru.vyatsu.fileconverter.core.transform;

import ru.vyatsu.fileconverter.core.model.json.*;
import ru.vyatsu.fileconverter.core.model.xml.Mangalib;
import ru.vyatsu.fileconverter.core.model.xml.Manhwa;
import ru.vyatsu.fileconverter.core.model.xml.TeamTranslation;

import java.util.*;

public class MangalibConverter {

    public static MangalibJson mangalibToMangalibJson(Mangalib mangalib) {
        return MangalibJson.builder()
                .mangalib(authorsToAuthorsJson(mangalib.getMangalib()))
                .build();
    }

    private static AuthorsJson authorsToAuthorsJson(List<Manhwa> manhwaList) {
        Map<String, Author> authorMap = new LinkedHashMap<>();

        for (Manhwa manhwa : manhwaList) {
            String authorName = manhwa.getAuthor();

            // Если автор уже существует, добавляем манхву к его списку
            if (authorMap.containsKey(authorName)) {
                authorMap.get(authorName).getAuthor().getManhws().add(manhwaToManhwaJson(manhwa));
            } else {
                // Создаем нового автора с манхвой и добавляем в мап
                authorMap.put(authorName, manhwaToAuthor(manhwa));
            }
        }

        return AuthorsJson.builder()
                .authors(new ArrayList<>(authorMap.values()))
                .build();
    }

    private static Author manhwaToAuthor(Manhwa manhwa) {
        return Author.builder()
                .author(convertManhwaToAuthorJson(manhwa))
                .build();
    }

    private static AuthorJson convertManhwaToAuthorJson(Manhwa manhwa) {
        return AuthorJson.builder()
                .name(manhwa.getAuthor())
                .manhws(new ArrayList<>(Collections.singletonList(manhwaToManhwaJson(manhwa))))
                .build();
    }

    private static ManhwaJson manhwaToManhwaJson(Manhwa manhwa) {
        return ManhwaJson.builder()
                .title(manhwa.getTitle())
                .publicationYear(manhwa.getPublicationYear())
                .chapters(manhwa.getChapters())
                .translationTeam(teamTranslationToTeamTranslationJson(manhwa.getTranslationTeam()))
                .build();
    }

    private static TeamTranslationJson teamTranslationToTeamTranslationJson(TeamTranslation teamTranslation) {
        return TeamTranslationJson.builder()
                .publicationProjects(teamTranslation.getPublicationProjects())
                .adminName(teamTranslation.getAdminName())
                .name(teamTranslation.getName())
                .build();
    }
}