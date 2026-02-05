package me.hakotsuki2003.nondyst.nondystmod;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

import java.util.ArrayList;
import java.util.LinkedList;

public class ExampleCommands {

    /**
     * example foo で呼び出す最も基本的なコマンド実行
     *
     * @param context コマンド呼び出しの環境
     * @return 常に成功を表す1を返す
     */
    public static int foo(CommandContext<CommandSourceStack> context) {
        CommandSourceStack source = context.getSource();
        source.sendSystemMessage(Component.literal("foo"));
        return 1;
    }

    /**
     * example bar hoge で呼び出す bar の下に位置する hoge サブコマンドの実行
     *
     * @param context コマンド呼び出しの環境
     * @return 常に成功を表す1を返す
     */
    public static int barHoge(CommandContext<CommandSourceStack> context) {
        CommandSourceStack source = context.getSource();
        source.sendSystemMessage(Component.literal("bar hoge"));
        return 1;
    }

    /**
     * example bar fuga で呼び出す bar の下に位置する fuga サブコマンドの実行
     *
     * @param context コマンド呼び出しの環境
     * @return 常に成功を表す1を返す
     */
    public static int barFuga(CommandContext<CommandSourceStack> context) {
        CommandSourceStack source = context.getSource();
        source.sendSystemMessage(Component.literal("bar fuga"));
        return 1;
    }

    /**
     * example baz 整数値　で呼び出す 引数付きコマンド
     *
     * @param context コマンド呼び出しの環境
     * @return 常に成功を表す1を返す
     */
    public static int baz(CommandContext<CommandSourceStack> context) {
        CommandSourceStack source = context.getSource();
        int x = IntegerArgumentType.getInteger(context, "x");
        String message = String.format(
                "baz: x = %d",
                x
        );
        source.sendSystemMessage(Component.literal(message));
        return 1;
    }

    /**
     * example quxシリーズは複雑な引数の登録の動作確認
     * example qux に引数が伴わない場合の使い方表示
     *
     * @param context コマンド呼び出しの環境
     * @return 常に成功を表す1を返す
     */
    public static int quxUsage(CommandContext<CommandSourceStack> context) {
        CommandSourceStack source = context.getSource();
        String prefix = String.format("%s %s", Nondystmod.MODID, "qux");
        String[] help = {
                String.format("usage: %s", prefix),
                String.format("%s: - : show this help.", prefix),
                String.format("%s: int : show the value.", prefix),
                String.format("%s: int, int : show these values.", prefix),
                String.format("%s: player : show the UUID of the player.", prefix),
        };
        for (String message : help) {
            source.sendSystemMessage(Component.literal(message));
        }
        return 1;
    }

    /**
     * example quxシリーズは複雑な引数の登録の動作確認
     * example qux 整数 の呼び出し: これは example qux 整数1 整数1 の整数2の省略形を実現できることの確認
     *
     * @param context コマンド呼び出しの環境
     * @return 常に成功を表す1を返す
     */
    public static int qux1(CommandContext<CommandSourceStack> context) {
        CommandSourceStack source = context.getSource();
        int x = IntegerArgumentType.getInteger(context, "x");
        String message = String.format(
                "qux1: (x) = (%d)",
                x
        );
        source.sendSystemMessage(Component.literal(message));
        return 1;
    }

    /**
     * example quxシリーズは複雑な引数の登録の動作確認
     * example qux 整数1 整数2 の呼び出し
     *
     * @param context コマンド呼び出しの環境
     * @return 常に成功を表す1を返す
     */
    public static int qux2(CommandContext<CommandSourceStack> context) {
        CommandSourceStack source = context.getSource();
        int x = IntegerArgumentType.getInteger(context, "x");
        int y = IntegerArgumentType.getInteger(context, "y");
        String message = String.format(
                "qux2: (x, y) = (%d, %d)",
                x,
                y
        );
        source.sendSystemMessage(Component.literal(message));
        return 1;
    }

    /**
     * example quxシリーズは複雑な引数の登録の動作確認
     * example qux プレイヤー型の呼び出し: これは example 整数 に対する型オーバーロードの呼び出しを実現できることの確認
     * example qux 1 -> ならば quxy1 が呼び出しできる
     * example qux @s -> ならば quxyPlayer が呼び出しできる
     *
     * @param context コマンド呼び出しの環境
     * @return 常に成功を表す1を返す
     */
    public static int quxPlayer(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        CommandSourceStack source = context.getSource();
        Player player = EntityArgument.getPlayer(context, "player");
        String message = String.format(
                "quxPlayer: player's UUID = %s",
                player.getUUID()
        );
        source.sendSystemMessage(Component.literal(message));
        return 1;
    }

    /**
     * 場に残っている全てのゾンビに落雷を与える
     *
     * @param context 環境
     * @return 成功の1
     */
    public static int battleThunder(CommandContext<CommandSourceStack> context) {
        CommandSourceStack source = context.getSource();
        source.sendSystemMessage(Component.literal("battle clear"));
        ServerLevel level = source.getLevel();

        LinkedList<LightningBolt> lightningBolts = new LinkedList<LightningBolt>();
        for (Entity entity : level.getEntities().getAll()) {
            if (entity instanceof Zombie zombie) {
                LightningBolt lightningBolt =
                        EntityType.LIGHTNING_BOLT.create(level);
                if (lightningBolt != null) {
                    lightningBolt.moveTo(zombie.getBoundingBox().getCenter());
                    lightningBolts.add(lightningBolt);
                    String message = String.format(
                            "You generated The LightningBolt for The Zombie: %s",
                            zombie.getUUID()
                    );
                    source.sendSystemMessage(Component.literal(message));
                } else {
                    String message = String.format(
                            "You could not generate The LightningBolt for The Zombie: %s",
                            zombie.getUUID()
                    );
                    source.sendSystemMessage(Component.literal(message));
                }
            }
        }
        for (LightningBolt lightningBolt : lightningBolts) {
            level.addFreshEntity(lightningBolt);
        }
        return 1;
    }


    /**
     * 場に残っている全てのゾンビを消し去る
     *
     * @param context 環境
     * @return 成功の1
     */
    public static int battleClear(CommandContext<CommandSourceStack> context) {
        CommandSourceStack source = context.getSource();
        source.sendSystemMessage(Component.literal("battle clear"));
        ServerLevel level = source.getLevel();

        for (Entity entity : level.getEntities().getAll()) {
            if (entity instanceof Zombie zombie) {
                String message = String.format(
                        "zombie discard %s",
                        zombie.getUUID()
                );
                source.sendSystemMessage(Component.literal(message));
                zombie.discard();
            }
        }
        return 1;
    }

    /**
     * 2体以上残ってる場合ゾンビ同士で戦いを継続させる
     *
     * @param context
     * @return ゾンビ同士の戦いが成立しない場合 0, そうでなければ 1
     */
    public static int battleContinue(CommandContext<CommandSourceStack> context) {
        CommandSourceStack source = context.getSource();
        source.sendSystemMessage(Component.literal("battle continue"));
        ServerLevel level = source.getLevel();
        RandomSource random = level.getRandom();
        LinkedList<Zombie> zombies = new LinkedList<Zombie>();
        for (Entity entity : level.getEntities().getAll()) {
            if (entity instanceof Zombie zombie) {
                zombies.add(zombie);
            }
        }
        source.sendSystemMessage(
                Component.literal(
                        String.format(
                                "battle continue, number of zombies=%d",
                                zombies.size()
                        )
                )
        );
        // バトルの成立には2体以上が必要
        if (zombies.size() < 2) {
            return 0;
        }
        for (int s = zombies.size(), w = s - 1, r = random.nextInt(w), i = s; --i >= 0; r = random.nextInt(w)) {
            int n = i + r - w;
            if (n < 0) {
                n = n + s;
            }
            zombies.get(i).setTarget(zombies.get(n));
            source.sendSystemMessage(
                    Component.literal(
                            String.format(
                                    "battle continue, The Zombies[%d] attack Zombies[%d].",
                                    i,n
                            )
                    )
            );
        }
        return 1;
    }

    /**
     * 場にある全てのゾンビに新しいゾンビを追加してバトルを開始する
     * 全てのヘルスは回復する
     *
     * @param num     生成するゾンビの数
     * @param context 環境
     * @return 成功の1
     */
    public static int battleStart(int num, CommandContext<CommandSourceStack> context) {
        CommandSourceStack source = context.getSource();
        source.sendSystemMessage(Component.literal("battle start"));
        Vec3 pos = source.getPosition();
        ServerLevel level = source.getLevel();
        RandomSource random = level.getRandom();
        ArrayList<Zombie> zombies = new ArrayList<>(num);
        Player player = source.getPlayer();
        if (player != null) {
            //既に存在するゾンビも対象にする
            zombies.addAll(
                    level.getEntitiesOfClass(
                            Zombie.class,
                            player.getBoundingBox().inflate(10)
                    )
            );
        }
        for (int i = num; --i >= 0; ) {
            Zombie zombie = EntityType.ZOMBIE.create(level);
            if (zombie == null) continue;
            zombies.add(zombie);
        }
        String message = String.format(
                "battle start : zombies %d",
                zombies.size()
        );
        source.sendSystemMessage(Component.literal(message));
        level.setDayTime(1800);//昼にするといずれ全部焼かれて死ぬっぽい

        if (zombies.isEmpty()) {
            return 0;
        }
        // ランダムな敵設定するゾンビの添字を計算で手に入れる
        // 他のゾンビを狙わせるのが目的なので自分自身をターゲットに設定しない
        // 全体で4個の場合で考える, 生成が必要な乱数の幅は自身の値を除くので3個分
        // それを自分より大きい方にずらせば自分自身を含まない値が得られる
        // ただし添字の最大値を超えた場合は0から折り返す必要がある
        // 例えば現在の添字が 2 である場合, 入手しなければならない値は 0,1,3 のいずれかである
        // 全体の要素数は 4 であるから,自分を除いた 幅 3 を乱数として得なければならない
        // ここで得られるのは　Rnadom.nextInt(3) の仕様により, 0,1,2 のいずれかである
        // 一旦自分 2 を足して自分含めた3種類の値を計算し 2,3,4 とする
        // そこで幅の 3 を引いて基準位置を 0 起点にすることで -1,0,1 が手に入る
        // 負の数になるということは 2 より右側を意味するので,配列の要素数 4 を足すことで添字の 3 が手に入る
        // これで常に自分を含まない敵の添字が計算できる
        // 以下, 4個の要素の配列の場合の計算過程のメモ
        // 3 , 0,1,2 -> 3,4,5 -> 0,1,2 -> 0,1,2
        // 2 , 0,1,2 -> 2,3,4 -> -1,0,1 -> 3,0,1
        // 1 , 0,1,2 -> 1,2,3 -> -2,-1,0 -> 2,3,0
        // 0 , 0,1,2 -> 0,1,2 -> -3,-2,-1 -> 1,2,3
        // s : 配列の要素数
        // w : 乱数の幅
        // r : 乱数の値
        // i : 現在の要素を示す添字
        for (int s = zombies.size(), w = s - 1, r = random.nextInt(w), i = s; --i >= 0; r = random.nextInt(w)) {
            int n = i + r - w;
            if (n < 0) {
                n = n + s;
            }
            source.sendSystemMessage(Component.literal(String.format("attacking: from %d to %d", i, n)));

            // 現在のゾンビ
            Zombie zombie = zombies.get(i);

            // 敵対するゾンビを設定する
            zombie.setTarget(zombies.get(n));

            // 戦いが長引くように弱くする
            AttributeInstance attackDamage = zombie.getAttribute(Attributes.ATTACK_DAMAGE);
            if (attackDamage != null) {
                attackDamage.setBaseValue(0.5);
            }

            // 戦いが長引くようにタフにする
            AttributeInstance maxHealth = zombie.getAttribute(Attributes.ATTACK_DAMAGE);
            if (maxHealth != null) {
                maxHealth.setBaseValue(00.0f);
            }
            zombie.setHealth(60.0f);

            // プレイヤーの周辺に適当に配置する
            zombie.setPos(
                    pos.x + random.nextInt(5) + 2,
                    pos.y + random.nextInt(5) + 2,
                    pos.z
            );
        }

        for (Zombie zombie : zombies) {
            level.addFreshEntity(zombie);
        }
        return 1;
    }

    /**
     * デフォルト値の4体でバトル開始
     *
     * @param context 環境
     * @return 成功の1
     */
    public static int battleStart(CommandContext<CommandSourceStack> context) {
        return battleStart(4, context);
    }

    /**
     * 指定された個体数でバトル開始
     *
     * @param context 環境
     * @return 成功の1
     */
    public static int battleStartNumber(CommandContext<CommandSourceStack> context) {
        int number = IntegerArgumentType.getInteger(context, "number");
        return battleStart(number, context);
    }

    /**
     * Modアノテーションのクラスから呼び出されてコマンド登録を行う実際の処理
     * Modアノテーション付与クラスで以下のような呼び出しを追加してこのregisterメソッドを呼び出す
     * <pre>
     * @SubscribeEvent
     * public void onRegisterCommands(RegisterCommandsEvent event) {
     *         //このregisterメソッドを呼び出すようにする
     *         ExampleCommands.register(event.getDispatcher());
     * }
     * </pre>
     *
     * @param dispatcher コマンド登録のためのディスパッチャ
     */
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(
                Commands.literal("example") //トップレベルのコマンドの名前
                        .then(Commands
                                .literal("battle") // fgtrjhyufoomod battle のサブコマンドグループ
                                .then(Commands
                                        .literal("start") // example battle start [<number>] コマンド
                                        .executes(ExampleCommands::battleStart)// <number> を省略し, デフォルト値の4体でバトル開始
                                        .then(Commands.argument("number", IntegerArgumentType.integer(4, 10))
                                                .executes(ExampleCommands::battleStartNumber) // number に 4 - 10体を指定してバトル開始
                                        )
                                )
                                .then(Commands
                                        .literal("clear") // example battle clear で実行するコマンド
                                        .executes(ExampleCommands::battleClear)
                                )
                                .then(Commands
                                        .literal("thunder") // example battle thunder で実行するコマンド
                                        .executes(ExampleCommands::battleThunder)
                                )
                                .then(Commands
                                        .literal("continue") // example battle continue で実行するコマンド
                                        .executes(ExampleCommands::battleContinue)
                                )
                        )
                        .then(Commands
                                .literal("foo") // example foo で実行するコマンド
                                .executes(ExampleCommands::foo)
                        )
                        .then(Commands
                                .literal("bar") // example bar のサブコマンドグループ
                                .then(Commands
                                        .literal("hoge") // example bar hoge で実行するコマンド
                                        .executes(ExampleCommands::barHoge)
                                )
                                .then(Commands
                                        .literal("fuga") // example bar fuga で実行するコマンド
                                        .executes(ExampleCommands::barFuga)
                                )
                        )
                        .then(Commands
                                .literal("baz") // example baz で実行するコマンド
                                .then(Commands.argument("x", IntegerArgumentType.integer())
                                        .executes(ExampleCommands::baz)
                                )
                        )
                        .then(Commands
                                .literal("qux") // example qux で実行するコマンド
                                .executes(ExampleCommands::quxUsage)// 引数なしで呼び出した場合
                                .then(Commands.argument("x", IntegerArgumentType.integer())
                                        .executes(ExampleCommands::qux1) // 引数の整数1で呼び出し
                                        .then(Commands.argument("y", IntegerArgumentType.integer())
                                                .executes(ExampleCommands::qux2)//引数が整数2つで呼び出し
                                        )
                                )
                                .then(Commands.argument("player", EntityArgument.player())
                                        .executes(ExampleCommands::quxPlayer)//引数がエンティティ1つで呼び出し
                                )
                        )
        );
    }
}
