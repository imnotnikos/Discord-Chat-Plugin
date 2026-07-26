# DiscordChatLogger

A lightweight Paper plugin that relays in-game chat, joins, leaves, and deaths to a Discord channel via webhook — no bot required.

[![GitHub](https://img.shields.io/badge/GitHub-imnotnikos-181717?style=flat-square&logo=github&logoColor=white)](https://github.com/imnotnikos)
[![Instagram](https://img.shields.io/badge/Instagram-imNikos_-E4405F?style=flat-square&logo=instagram&logoColor=white)](https://instagram.com/imnikos_)
[![YouTube](https://img.shields.io/badge/YouTube-imNikoss-FF0000?style=flat-square&logo=youtube&logoColor=white)](https://youtube.com/@imnikoss)
[![TikTok](https://img.shields.io/badge/TikTok-imNikos_-000000?style=flat-square&logo=tiktok&logoColor=white)](https://tiktok.com/@imnikos_)
---

## Overview

DiscordChatLogger is a minimal bridge between your Minecraft server and Discord. It listens for a handful of core server events and posts them to a Discord webhook as plain messages, so your community can follow along with what's happening in-game without needing a full Discord bot.

## Features

- 💬 **Chat relay** — every player message is forwarded to Discord.
- 🟢 **Join announcements** — posts when a player joins the server.
- 🔴 **Leave announcements** — posts when a player disconnects.
- ☠️ **Death messages** — posts the server's death message when a player dies.
- No external dependencies beyond the Paper API — uses Java's built-in `HttpClient` to talk to Discord, so there's nothing extra to shade or bundle.

## Requirements

- A Paper server (or Paper-based fork) running Minecraft **1.21.x**.
- Java 21+.
- A Discord webhook URL for the channel you want messages posted to (Discord → Channel Settings → Integrations → Webhooks → New Webhook).

## Building

This is a standard Maven project.

```bash
mvn clean package
```

The compiled plugin jar will be in `target/`.

## Setup

Before building, you need to point the plugin at your Discord webhook:

1. Open `src/main/java/com/imNikos/DiscordNotifier.java`.
2. Replace the placeholder in this line with your actual webhook URL:
   ```java
   private static final String WEBHOOK_URL = "Webhook_URL";
   ```
3. Build the plugin (`mvn clean package`) and drop the resulting jar into your server's `plugins/` folder.
4. Restart or reload your server.

> **Note:** The webhook URL is currently compiled directly into the plugin rather than read from a config file. Treat your built jar as containing a secret — don't share it publicly with your real webhook URL baked in, and re-build if you ever need to rotate the webhook.

## How It Works

`DiscordChatLogger` (the main plugin class) registers a single listener, `ServerEventsListener`, on enable. That listener hooks four Bukkit/Paper events — chat, join, quit, and death — and passes a formatted string to `DiscordNotifier.sendToDiscord(...)`, which fires an asynchronous POST request containing a simple `{"content": "..."}` JSON payload to your webhook URL.

## Limitations

- No config file yet — the webhook URL and message formats are hardcoded, so any changes require rebuilding the plugin.
- No filtering, rate-limiting, or per-world/per-event toggles — everything listed above is always forwarded.
- Message content is sent as-is (with quotes escaped for JSON safety), so no Discord-side formatting beyond what you see in-game (bold names, emoji prefixes) is customizable without editing the source.

## License

No license file is currently included — add one if you plan to distribute this publicly.
