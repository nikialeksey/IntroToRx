(ns rxjs.routes.home
  (:require [rxjs.layout :as layout]
            [compojure.core :refer [defroutes GET POST]]
            [ring.util.http-response :as response]
            [clojure.java.io :as io])
  (:use hiccup.core)
  (:use hiccup.form)
  (:use hiccup.page))

(defn pres-page []
  (let [slide-1 [:section
                 [:section {:data-notes "Привет! Слово 'реактивное' станет зеленым."}
                  [:h1 "ReactiveX"]
                  [:p "Краткое введение в "
                   [:span.fragment.highlight-green "реактивное "]
                   [:span "программирование"]]]
                 [:section {:data-background-color "#4A148C" :data-notes "Вся презентация будет скучная, конец дня все-таки. 
Иногда будет какая-нибудь ненормальная картинка. В самом завершении презентации будет немного непростого для понимая кода. "}
                  [:h2 "Сегодня в программе:"]
                  [:ul
                   [:li "Все мы пишем интерфейсы"]
                   [:li "Кусочек функциональщины в нашем привычном мире"]
                   [:li "Пример простой реализации непростого интерфейса"]]]]
        
        slide-2 [:section
                 [:section {:data-notes "Что есть общего между мобильными разработчиками и фронтэнд разработчиками? - вот какой вопрос ставится"}
                  [:h2 "Android-разработка - это фронтэнд"]
                  [:ul
                   [:li.fragment "В большинстве сулчаев - "
                    [:mark "тонкий клиент"]]
                   [:li.fragment "Человеко-понятные, адаптивные, платформо-стилизованные "
                    [:mark "интерфейсы"]]]]
                 [:section {:data-background-color "#4A148C" :data-notes "Это значит, что мобильные разработчики теперь 
стали и десктоп разработчиками, ну не кртуто ли! Тепрь между веб и мобильной разработкой еще больше общего."}
                  [:h2 "Andrdoid-приложения уже могут запускаться в Chrome!"]
                  [:div {:data-markdown true}
                   "[https://developer.chrome.com/apps/getstarted_arc](https://developer.chrome.com/apps/getstarted_arc)"]
                  [:img {:data-src "/img/arc.png"}]]
                 [:section
                  [:h4 "Эт я все к чему..."]
                  [:h2 "Тонкостей на каждой платформе хватает, но интерфейсы пилят все."]]]

        slide-3 [:section
                 [:section {:data-notes "Очень тяжело реализовывать, еще тяжелее отлаживать, еще тяжелее тестировать."}
                  [:h2 "Сложные интерфейсы - это головная боль"]
                  [:img {:data-src "/img/боль.jpg"}]]
                 [:section {:data-background-color "#4A148C"}
                  [:h2 "Это все потому, что:"]
                  [:ul
                   [:li "Нужно поддерживать все данные в " [:mark "актуальном"] " состоянии"]
                   [:li.fragment "Нужно учитывать как можно " [:mark "больше вариантов"] " развития событий"]
                   [:li.fragment "Нужно быть готовым к " [:mark "постоянным изменениям"] " условий"]
                   [:li.fragment [:img {:data-src "/img/hard_world.png"}]]]]
                 [:section {:data-notes "Всплывет сейчас Микки!"}
                  [:h3 "Но если их осилить, то пользователь будет вам благодарен"]
                  [:img.fragment {:data-src "/img/хуй.jpg"}]]]
        
        slide-4 [:section
                 [:section {:data-background-color "#4A148C" :data-notes "В аннотации к докладу вы, возможно прочитали слова 'Функциональное программирование', 
так вот, мы сейчас немного поговорим про ФП, а потом про реактивное программирование"}
                  [:h2 "Что-то там про функциональщину было..."]
                  [:h4 "Характерно для ФП:"]
                  [:ul
                   [:li [:mark "Иммутабельные"] " данные, структуры данных"]
                   [:li.fragment "Высокий уровень " [:mark "абстракции"]]
                   [:li.fragment [:mark "Чистые"] " функции"]
                   [:li.fragment "Гомоиконность"]]]
                 [:section {:data-notes "Доклад уже скучный, так что надо очень быстро показать эту картинку"}
                  [:h2 "Гомоиконность???"]
                  [:img.fragment {:data-src "/img/геи.jpg"}]]]
        
        slide-5 [:section
                 [:section {:data-notes "Все таки так сам Алан Кей сказал, создатель ООП. Почему так? Ну а вы как сами думаете? Вот кто из вас пишет на ФП?"}
                  [:h2 "ФП - это не просто"]
                  [:blockquote "Сменить точку зрения - это +80 к IQ"]]
                 [:section {:data-notes "Надо привести пример про ребусы, или сказать как это сложно, когда у тебя под руками нет переменных, что думать надо совсем другими конструкциями, высокими абстракциями."}
                  [:h4 "Когда только начал вникать в ФП тебе кажется, что ты решаешь ребусы"]
                  [:img {:data-src "/img/ребус.jpg"}]]
                 [:section {:data-background-color "#4A148C" :data-notes "Абстракции и иммутабельность остались, хорошо это или плохо в имепартивных языка - 
судите сами. Сказать про то, что иммутабельность тянет за собой недостатки, абстракции в свою очередь приносят лаконичность, больше смысла на строчку кода. 
Ну и, самое главное, все то, на чем построен реактив - события. Все - это события."}
                  [:h2 "Реактивное программирование построено на парадигмах ФП"]
                  [:ul
                   [:li [:mark "Иммутабельные"] " данные, структуры данных"]
                   [:li "Высокий уровень " [:mark "абстракции"]]
                   [:li.fragment "Потоки " [:mark "событий"]]]]]

        slide-6 [:section
                 [:section {:data-notes "Нужно пояснить картинку, пусть некоторый пользователь захотел ввести в текстовое поле слово, тогда как бы выглядели
 события во времени? Не забыть кликнуть вниз, там полезная фраза о том, что осталось научится управлять событиями."}
                  [:h2 "Воспринимайте все как события и вы поймете реактивное программирование"]
                  [:img.fragment {:data-src "/img/падла.png"}]
                  [:p.fragment "Осталось научится управлять событиями и подписываться на них."]]
                 [:section {:data-background-color "#4A148C"}
                  [:h1 "Перейдем к теории"]]]

        slide-7 [:section
                 [:section {:data-notes "Пара терминов."}
                  [:h2 "Observables and Subscribers"]
                  [:p "Observables - объекты, излучающие события"]
                  [:p "Subscribers - объекты, слушающие события"]
                  [:pre
                   [:code.js "// $input - текстовое поле
var keyups = Rx.Observable.fromEvent($input, 'keyup') // Observable
keyups.subscribe(event => console.log(event)) // Subscriber
"]]]
                 [:section {:data-notes "Операторов очень много, они управляют потоками событий. На следующем слайде будет про ошибки, об этом надо
сказать сейчас, что ошибки в процессе преобразования событий могут происходить и это нормально, есть способы обработки таких ситуаций."}
                  [:h4 "На поток событий можно влиять с помощью операторов"]
                  [:pre
                   [:code.js "// $input - текстовое поле
var keyups = Rx.Observable
                  .fromEvent($input, 'keyup')
                  .pluck('target', 'value') // вытащим текст из события 
keyups.subscribe(text => console.log(text)) // вывод текста на консоль
"]]]
                 [:section {:data-notes "Ошибки ловит подписчик ... "}
                  [:h4 "Ошибки можно ловить в подписке"]
                  [:pre
                   [:code.js "
Rx.Observable
    .just(1)
    .map(i => {
        throw new Error('Ошибка!')
    })
    .subscribe(i => console.log(i), e => console.log(e)) // Будет выведена ошибка на консоль
"]]]
                 [:section {:data-notes "... и сразу отписывается после того, как словил ошибку. Лично мне это не очень нравится, объяснить ситуацию с филтрацией потока событий."}
                  [:h4 "Ошибки отписывают подписки, даже если события после ошибки будут излучаться"]
                  [:pre
                   [:code.js "
Rx.Observable.from([1, 2])
    .map(i => {
        if (i == 1) {
            throw new Error('Padla!')
        }
        return i
    })
    .subscribe(i => console.log(i), e => console.log(e)) // На консоле будет Error: Padla!
"]]]

                 [:section {:data-background-color "#4A148C" :data-notes "Пора бы уже"}
                  [:h1 "Перейдем к практике"]]]

        slide-8 [:section
                 [:section {:data-notes "Вот суть этой задачи в том, что просто так на VanilaJS ее не написать быстро и без ошибок, надо объяснить задачу,
а потом спросить, как бы это можно было реализовать?"}
                  [:h2 "Задача: форма регистрации"]
                  [:ul
                   [:li "Одно поле: логин"]
                   [:li "Есть ограничения на формат вводимых данных"]
                   [:li.fragment "Верификацию полей нужно производить в " [:mark "реалтайме"] ", но так, чтобы не сильно нагружать сервер постоянными запросами на проверку"]]]
                 
                 [:section {:data-set "Посмотрим сразу на результат. Он выглядит калично, но работает как положено, анимацию б к нему еще добавить и вобще красота будет. Ладно, пора объяснить как там все устроено"}
                  [:h2 "Результат"]
                  [:div
                   (form-to [:post "/register"]
                            [:fieldset.form-group
                             (text-field {:id "username" :placeholder "Username" :class "form-control"} "username")
                             [:div.form-control-feedback]]
                            (submit-button {:class "btn btn-primary"} "Register"))]]]

        slide-9 [:section
                 [:section {:data-notes "Здесь все просто. Все так умеют."}
                  [:h4 "Проверка имени пользователя в соответствии с шаблоном"]
                  [:pre
                   [:code.js "
var usernameLocalVerifier = username => {
    if (username.length < 3 || username.length > 20) {
	throw new Error('Длина имени пользователя должна быть не меньше 3 и не больше 20 символов.')
    }

    if (!/^[a-z]*$/.test(username)) {
	throw new Error('Имя пользователя должно состоять из латинских букв.')
    }
	
    return username
}"]]]
                 [:section {:data-notes "Уже что-то новенькое, надо рассказать про retryWhen, сказать для чего он нужен"}
                  [:h4 "Цепочка для локальной проверки имени пользователя"]
                  [:pre
                   [:code.js "
Rx.Observable.fromEvent($username, 'keyup')
    .pluck('target', 'value')
    .map(usernameLocalVerifier)
    .retryWhen(e => { // здесь переподпишемся на события 
	return e.map(_e => { 
	    error($username, _e.message) // и выведем ошибку
	    return e
	})
    })
   .subscribe(username => {
	success($username)
   })"]]]
                 [:section {:data-notes "Здесь уже сложнее. Мы впервые создаем свой собственный Observable из ajax запроса."}
                  [:h4 "Проверка имени пользователя с использованием апи"]
                  [:pre
                   [:code.js "
var usernameApiVerifier = username => {
    return Rx.Observable.create(s => {
	$.ajax({
	    url: '/username',
	    method: 'POST',
	    data: {username: username},
	    success: r => s.onNext(r)
	})
    })
	.map(res => {
	    console.log(res.result)
	    if (res.result) eturn username
            else hrow new Error('Такое имя уже существует.')
	})
}"]]]
                 [:section {:data-notes "А теперь объеденим две проверки в одной цепочке! Это уже интересно. Но есть одно но в следующем слайде"}
                  [:h4 "Добавляем апишную проверку в основную цепочку"]
                  [:pre
                   [:code.js "
Rx.Observable.fromEvent($username, 'keyup')
    .pluck('target', 'value')
    .map(usernameLocalVerifier)
    .flatMap(usernameApiVerifier)
    .retryWhen(e => {
	return e.map(_e => {
	    error($username, _e.message)
	    return e
	})
    })
   .subscribe(data => {
	success($username)
   })"]]]
                 [:section {:data-notes "Вот мы же хотели сервер-то не нагружать! А получается, что шлем ему запросы на каждое изменение текстового поля"}
                  [:h4 "Но мы теперь на каждое изменение будем отправлять ajax! А как же условие не нагружать сервер?!"]
                  [:pre.fragment
                   [:code.js {:data-trim true :data-noescape true} "
Rx.Observable.fromEvent($username, 'keyup')
    .pluck('target', 'value')
    " [:mark ".debounce(2000)"] "
    .map(usernameLocalVerifier)
    .flatMap(usernameApiVerifier)
    .retryWhen(e => {
	return e.map(_e => {
	    error($username, _e.message)
	    return e
	})
    })
   .subscribe(data => {
	success($username)
   });
"]]]
                 [:section {:data-notes "Позволяет управлять потоком событий во времени. Это очень круто) Дальше последний слайд."}
                  [:h4 "Debounce - это еще что?"]
                  [:img {:data-src "/img/хак.jpg"}]
                  [:blockquote.fragment "В данном случае это способ ограничить плотность событий на единицу времени. "]]]
        
        slide-10 [:section {:data-notes "Вот и все, ну и что скажете мне вы? Ну рассказал, ну и (тут надо нажать на кнопку). 
Проблемы-то полюбому есть какие-то. Проблемы есть: сложность придумки алгоритма, сложность отладки - это довольно серьезные вещи.
Дальше будут ссылки на документацию и материал"}
                  [:section {:data-background-color "#4A148C"}
                   [:h2 "Вот и все"]
                   [:img.fragment {:data-src "/img/заебись.jpg"}]]
                  [:section {:data-background-color "#4A148C"}
                   [:h2 "Ссылки:"]
                   [:div {:data-markdown true}
                    "
#### ReactiveX
- [github.com/Reactive-Extensions/RxJS/](https://github.com/Reactive-Extensions/RxJS/)
- [reactivex.io/](http://reactivex.io/)

#### Презентация на движке
- [lab.hakim.se/reveal-js/](http://lab.hakim.se/reveal-js/)
"]]]]
    
    (html5
     [:head
      [:title "ReactiveX"]
      (include-css
       "/assets/reveal.js/css/reveal.css"
       "/assets/reveal.js/css/theme/white.css"
       "/assets/reveal.js/lib/css/zenburn.css"
       "/assets/bootstrap/css/bootstrap.min.css"
       "/assets/font-awesome/css/font-awesome.min.css")]
     [:body
      [:div.reveal
       [:div.slides
        slide-1
        slide-2
        slide-3
        slide-4
        slide-5
        slide-6
        slide-7
        slide-8
        slide-9
        slide-10]]
      (include-js
       "/assets/jquery/jquery.min.js"
       "/assets/tether/dist/js/tether.min.js"
       "/assets/bootstrap/js/bootstrap.min.js"
       "/assets/rxjs/rx.all.js"
       "/assets/RxJS-DOM/rx.dom.js"
       "/js/main.js"
       "/assets/reveal.js/js/reveal.js"
       "/assets/reveal.js/lib/js/head.min.js"
       "/assets/reveal.js/plugin/notes/notes.js"
       "/js/pres.js")])))


(defroutes home-routes
  (GET "/" [] (pres-page))
  (POST "/username" [] (fn [req]
                         (let [username (get-in req [:params :username])]
                           (if (= username "asd")
                             {:body {:result false}}
                             {:body {:result true}})))))

