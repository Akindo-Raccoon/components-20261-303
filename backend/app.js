import { gameData } from './data.js';
import express from 'express';

const app = express()
const port = 3000


app.get('/', (req, res) => {
  res.send('Running app!')
})

app.get('/category/:category/language/:language/discovery', (req, res) => {
  const { category, language } = req.params;

  console.log(category)
  console.log(language)

  const list = gameData[language] && gameData[language][category];

  if (!list) {
    return res.status(404).send({ 
      error: "Categoría o idioma no encontrados" 
    });
  }

  const randomIndex = Math.floor(Math.random() * list.length);
  const selectedItem = list[randomIndex];

  res.send({
    clue: selectedItem.clue,
    word: selectedItem.word,
    category: category,
    language: language
  });
});

app.listen(port, () => {
  console.log(`App listening on port ${port}`)
})
