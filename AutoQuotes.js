// Author: Muhammad Siddique
// File: AutoQuotes.js
// 🎯 Hacktoberfest 2025 Contribution
// Description: A simple Auto Quote Generator that prints a random motivational quote every few seconds in the console.

const quotes = [
  "🌟 Believe in yourself, because you are stronger than you think.",
  "🚀 Success doesn’t come from what you do occasionally, it comes from what you do consistently.",
  "🔥 The harder you work for something, the greater you’ll feel when you achieve it.",
  "🌈 Don’t limit your challenges, challenge your limits.",
  "💡 Every great developer you know started as a beginner.",
  "🏁 Keep learning, keep building — your time is coming!",
  "⚡ Failure is simply the opportunity to begin again, this time more intelligently.",
  "✨ Code. Debug. Learn. Repeat. That’s how legends are made.",
  "💻 Stay curious. Keep coding. Be unstoppable.",
  "📈 The only way to go is up — keep pushing forward!"
];

// Function to get a random quote
function getRandomQuote() {
  const randomIndex = Math.floor(Math.random() * quotes.length);
  return quotes[randomIndex];
}

// Function to print quotes automatically every few seconds
function startAutoQuotes(interval = 5000) {
  console.log("🚀 Auto Quote Generator Started! (Press Ctrl + C to stop)\n");
  setInterval(() => {
    console.log(getRandomQuote());
  }, interval);
}

// Run the auto quote generator
startAutoQuotes();

// Export for Node.js or external usage
module.exports = { getRandomQuote, startAutoQuotes };
