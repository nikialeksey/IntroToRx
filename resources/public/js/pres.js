Reveal.initialize({

    // Parallax background image
    //parallaxBackgroundImage: "http://keitheis.github.io/reactive-programming-in-python/pictures/rx.jpg",
    // Parallax background size
    //parallaxBackgroundSize: "1800px 1120px",

    width: 1280,
    height: 700,
    
    history: true,

    slideNumber: true,
    
    dependencies: [
	// Interpret Markdown in <section> elements
        { src: 'assets/reveal.js/plugin/markdown/marked.js', condition: function() { return !!document.querySelector( '[data-markdown]' ); } },
        { src: 'assets/reveal.js/plugin/markdown/markdown.js', condition: function() { return !!document.querySelector( '[data-markdown]' ); } },
	// Syntax highlight for <code> elements
        { src: 'assets/reveal.js/plugin/highlight/highlight.js', async: true, callback: function() { hljs.initHighlightingOnLoad(); } },
    ]
})
