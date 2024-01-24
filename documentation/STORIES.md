# Opis wymagań technicznych oraz User Stories

W poniższym dokumencie znajdziesz opis wymagań technicznych aplikacji, user stories oraz opis
proponowanej przez nas domeny.
Nie przejmuj się zabezpieczeniami, autoryzacją czy autentykacją. Nie są one przedmiotem tego
zadania. Api powinno być otwarte i możliwe do przetestowania przez nas za pomocą narzędzia podobnego
do postmana.

# Wymagania techniczne

- Komunikacja z backendem aplikacji powinna odbywać się poprzez REST API. Odpowiedzi z serwera
  powinny być w formacie JSON.
- Api powinno być otwarte - bez jakiejkolwiek autoryzacji.
- Kod powinien być odpowiednio udokumentowany.
- Przynajmniej podstawowy zestaw testów dla scenariuszy happy path.
- Wybór technologii magazynowania danych należy do Ciebie (pamięć, pliki, in-memory db, RDBMS, NoSQL
  itp).
- Wybór dodatkowych narzędzi również należy do Ciebie.
- Pamiętaj jednak o łatwości uruchomienia projektu przez nas, jeżeli projekt będzie wymagał
  dodatkowych działań - udokumentuj je.

# User Stories

| **Title**                | **User Story**                                                      | Dane wejsciowe                                               | Dane wyjściowe                                                |
|--------------------------|---------------------------------------------------------------------|--------------------------------------------------------------|---------------------------------------------------------------|
| Utwórz konfigurację      | Jako użytkownik chciałbym mieć możliwość utworzenia konfiguracji.   | Identyfikator urządzenia (tekst), konfiguracja (tekst, json) | Wszystkie pola klasy domenowej konfiguracja                   |
| Odczytaj konfigurację    | Jako użytkownik chciałbym mieć możliwość odczytania konfiguracji.   | Identyfikator bazodanowy konfiguracji                        | Tylko pola "konfiguracja" oraz "data utworzenia" (yyyy-mm-dd) |
| Zaktualizuj konfigurację | Jako użytkownik chciałbym mieć możliwość aktualizacji konfiguracji. |                                                              | Wszystkie pola klasy domenowej konfiguracja                   |
| Usuń konfigurację        | Jako użytkownik chciałbym mieć możliwość usunięcia konfiguracji.    | Identyfikator bazodanowy konfiguracji                        | Brak                                                          |


Przykładowe żądania i kształt danych wejściowych znajdziesz w katalogu documentation/examples repozytorium.
# Analiza domeny
Urządzenie IoT może posiadać jeden adres oraz dowolną ilość konfiguracji.

Urządzenie IoT jest reprezentowane przez poniższe pola:

| **Pole**                 | **Opis**                                 | Dodatkowe informacje |
|--------------------------|------------------------------------------|----------------------|
| Klucz główny             | Klucz główny                             | ~                    |
| Identyfikator urządzenia | Tekst do 128 znaków                      | wymagane, unikalne   |
| Data utworzenia          | Datatime z informacją o strefie czasowej | wymagane             |
| Data modyfikacji         | Datatime z informacją o strefie czasowej | opcjonalne           |
| Data uruchomienia        | Datatime z informacją o strefie czasowej | opcjonalne           |
| Data wyłączenia          | Datatime z informacją o strefie czasowej | opcjonalne           |
| Adres                    | Datatime z informacją o strefie czasowej | opcjonalne           |

Adres jest reprezentowany przez poniższe pola:

| **Pole**      | **Opis**            | Dodatkowe informacje |
|---------------|---------------------|----------------------|
| Nazwa         | Tekst do 128 znaków | opcjonalne           |
| Ulica         | Tekst do 128 znaków | wymagane             |
| Numer budynku | Tekst do 128 znaków | wymagane             |
| Numer lokalu  | Tekst do 128 znaków | opcjonalne           |
| Miasto        | Tekst do 128 znaków | wymagane             |
| Kod pocztowy  | Tekst do 128 znaków | wymagane             |
| Kraj          | Tekst do 128 znaków | wymagane             |

Konfiguracja urządzenia IoT jest reprezentowana przez poniższe pola:

| **Title**                | **Opis**                                 | Dodatkowe informacje |
|--------------------------|------------------------------------------|----------------------|
| Klucz główny             | Klucz główny                             | ~                    |
| Identyfikator urządzenia | Tekst do 128 znaków                      | Wymagane             |
| Klucz obcy urządzenia    | ~                                        | Wymagane             |
| Konfiguracja             | JSON do 10k znaków                       | Wymagane             |
| Data utworzenia          | Datatime z informacją o strefie czasowej | Wymagane             |
| Data modyfikacji         | Datatime z informacją o strefie czasowej | Wymagane             |

